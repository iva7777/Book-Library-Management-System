package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.mapper.BookMapper;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
import com.example.booklibrary.library.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookDto bookDto;

    @BeforeEach
    void setUp() {
        bookMapper = new BookMapper();
        bookService = new BookServiceImpl(bookRepository, bookMapper);

        book = new Book();
        book.setId(1);
        book.setTitle("test");
        book.setPublisher("test");
        book.setIsbn("5145-124-544");
        book.setYearOfProduction(2003);
        book.setGenre("comics");
        book.setStatus(BookStatus.available);

        bookDto = new BookDto(1, "test", "test", "5145-124-544", "comics", BookStatus.available);
    }

    @Test
    void shouldGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<>(List.of(book)));

        String bookTitle = "test";
        String publisher = "test";
        String isbn = "5145-124-544";
        String genre = "comics";
        BookStatus status = BookStatus.available;

        List<BookDto> books = bookService.getAllBooks();

        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals(bookTitle, books.getFirst().title());
        Assertions.assertEquals(publisher, books.getFirst().publisher());
        Assertions.assertEquals(isbn, books.getFirst().isbn());
        Assertions.assertEquals(genre, books.getFirst().genre());
        Assertions.assertEquals(status, books.getFirst().status());
    }

    @Test
    void shouldGetBookById() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Optional<BookDto> newDtoOptional = bookService.getBookById(1);

        Assertions.assertTrue(newDtoOptional.isPresent());
        BookDto newDto = newDtoOptional.get();
        Assertions.assertEquals(bookDto, newDto);
        verify(bookRepository).findById(1);
    }

    @Test
    void shouldNotGetBookById() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        Optional<BookDto> newDtoOptional = bookService.getBookById(1);

        Assertions.assertTrue(newDtoOptional.isEmpty());

        verify(bookRepository).findById(1);
    }

    @Test
    void shouldUpdateBookStatus() {
        BookStatus newStatus = BookStatus.available;
        book = new Book();
        book.setId(1);
        book.setStatus(BookStatus.borrowed);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        Optional<BookDto> updatedBookOptional = bookService.updateBookStatus(1, newStatus);

        Assertions.assertTrue(updatedBookOptional.isPresent());
        Assertions.assertEquals(newStatus, updatedBookOptional.get().status());
        verify(bookRepository).save(book);
    }

    @Test
    void shouldNotUpdateBookStatus() {
        BookStatus newStatus = BookStatus.available;

        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        Optional<BookDto> updatedBookOptional = bookService.updateBookStatus(1, newStatus);

        Assertions.assertTrue(updatedBookOptional.isEmpty());
        verify(bookRepository).findById(1);
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void shouldDeleteBook() {
        bookService.deleteBook(1);

        verify(bookRepository).deleteById(1);
    }

    @Test
    void shouldSearchBooksByTitle() {
        String bookTitle = "test";

        when(bookRepository.findByTitleContaining(bookTitle)).thenReturn(new ArrayList<>(List.of(book)));

        List<BookDto> books = bookService.searchBooksByTitle(bookTitle);

        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals(bookTitle, books.getFirst().title());
    }

    @Test
    void shouldSearchBooksByTitleAndReturnEmptyList() {
        when(bookRepository.findByTitleContaining(anyString())).thenReturn(new ArrayList<>());

        List<BookDto> books = bookService.searchBooksByTitle("test");

        Assertions.assertEquals(0, books.size());
    }

    @Test
    void shouldSearchBooksByGenre() {
        String genre = "comics";

        when(bookRepository.findByGenre(genre)).thenReturn(new ArrayList<>(List.of(book)));

        List<BookDto> books = bookService.searchBooksByGenre(genre);

        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals(genre, books.getFirst().genre());
    }

    @Test
    void shouldSearchBooksByGenreAndReturnEmptyList() {
        when(bookRepository.findByGenre(anyString())).thenReturn(new ArrayList<>());

        List<BookDto> books = bookService.searchBooksByGenre("comic");

        Assertions.assertEquals(0, books.size());
    }

    @Test
    void shouldSearchBookByIsbnAndStatus() {
        String isbn = "test";
        BookStatus status = BookStatus.available;

        when(bookRepository.findBookByIsbnAndStatus(isbn, status)).thenReturn(Optional.of(book));

        bookDto = bookService.searchBookByIsbnAndStatus(isbn, status);

        Assertions.assertNotNull(bookDto);
        verify(bookRepository).findBookByIsbnAndStatus(isbn, status);
    }

    @Test
    void shouldSearchBookByIsbnAndStatusAndReturnOptional() {
        String isbn = "1234567890";
        BookStatus status = BookStatus.available;

        when(bookRepository.findBookByIsbnAndStatus(isbn, status)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            bookService.searchBookByIsbnAndStatus(isbn, status);
        });

        Assertions.assertEquals("Book with ISBN " + isbn + " and status " + status + " not found.", exception.getMessage());
    }
}