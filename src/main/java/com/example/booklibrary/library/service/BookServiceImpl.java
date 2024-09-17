package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.mapper.BookMapper;
import com.example.booklibrary.library.model.*;
import com.example.booklibrary.library.repository.AuthorBookRepository;
import com.example.booklibrary.library.repository.AuthorRepository;
import com.example.booklibrary.library.repository.BookRepository;
import com.example.booklibrary.library.service.interfaces.BookService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorBookRepository authorBookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(
            @NotNull BookRepository bookRepository,
            @NotNull AuthorRepository authorRepository,
            @NotNull AuthorBookRepository authorBookRepository,
            @NotNull BookMapper bookMapper
    ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorBookRepository = authorBookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {

        return bookRepository
                .findAll()
                .stream()
                .map(bookMapper::mapEntityToDto)
                .toList();
    }

    public Optional<BookDto> getBookById(int id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapEntityToDto);
    }

    public Book saveBook(BookDto bookDto) {
        Book book = bookMapper.mapDtoToEntity(bookDto);

        Book savedBook = bookRepository.save(book);
        validateAuthors(savedBook, bookDto.authorNames());

        return savedBook;
    }

    public Optional<BookDto> updateBookStatus(int bookId, BookStatus newStatus) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setStatus(newStatus);
                    Book updatedBook = bookRepository.save(book);
                    return bookMapper.mapEntityToDto(updatedBook);
                });
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

    private void validateAuthors(Book book, String authorNames) {
        if (authorNames == null || authorNames.trim().isEmpty()) {
            return;
        }

        book.setAuthorBooks(new ArrayList<>());
        String[] names = authorNames.split(",\\s*");
        List<Author> authors = new ArrayList<>();

        for (String name : names) {
            String[] splitName = name.trim().split("\\s+");

            AuthorBookId id = new AuthorBookId();

            if (splitName.length >= 2) {
                String firstName = splitName[0];
                String lastName = splitName[1];

                Optional<Author> optionalAuthor = authorRepository.findByFirstNameAndLastName(firstName, lastName);

                if (!optionalAuthor.isPresent()) {
                    Author author = new Author();
                    author.setFirstName(firstName);
                    author.setLastName(lastName);

                    id.setAuthorId(authorRepository.save(author));
                } else {
                    id.setAuthorId(optionalAuthor.get());
                }

                id.setBookId(book);
                AuthorBook authorBook = new AuthorBook();
                authorBook.setId(id);
                authorBookRepository.save(authorBook);
            }
        }
    }
}
