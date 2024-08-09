package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.mapper.BookMapper;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
import com.example.booklibrary.library.repository.BookRepository;
import com.example.booklibrary.library.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {

        return bookRepository
                .findAll()
                .stream()
                .map(bookMapper::mapEntityToDto)
                .toList();
    }

    public BookDto getBookById(int id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book with ID " + id + " not found."));

        return bookMapper.mapEntityToDto(book);
    }

    public Book saveBook(BookDto bookDto) {
        Book book = bookMapper.mapDtoToEntity(bookDto);

        return bookRepository.save(book);
    }

    public void updateBookStatus(int bookId, BookStatus newStatus) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("Book with ID " + bookId + " not found."));
        ;

        book.setStatus(newStatus);
        bookRepository.save(book);
    }

    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }

    public List<BookDto> searchBooksByTitle(String title) {
        return bookRepository
                .findByTitleContaining(title)
                .stream()
                .map(bookMapper::mapEntityToDto)
                .toList();
    }

    public List<BookDto> searchBooksByAuthorName(String authorName) {
        return bookRepository
                .findByAuthorNameContaining(authorName)
                .stream()
                .map(bookMapper::mapEntityToDto)
                .toList();
    }

    public List<BookDto> searchBooksByGenre(String genre) {
        return bookRepository
                .findByGenre(genre)
                .stream()
                .map(bookMapper::mapEntityToDto)
                .toList();
    }

    public List<BookDto> searchBooks(String query) {
        List<Book> booksByTitle = bookRepository.findByTitleContaining(query);
        List<Book> booksByAuthorName = bookRepository.findByAuthorNameContaining(query);
        List<Book> booksByGenre = bookRepository.findByGenre(query);

        booksByTitle.addAll(booksByAuthorName);
        booksByTitle.addAll(booksByGenre);

        return booksByTitle
                .stream()
                .distinct()
                .map(bookMapper::mapEntityToDto)
                .toList();
    }

    public BookDto searchBookByIsbnAndStatus(String isbn, BookStatus status) {
        Book book = bookRepository
                .findBookByIsbnAndStatus(isbn, status)
                .orElseThrow(() -> new NoSuchElementException("Book with ISBN " + isbn + " and status " + status + " not found."));

        return bookMapper.mapEntityToDto(book);
    }
}
