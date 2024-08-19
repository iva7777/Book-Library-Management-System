package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
import com.example.booklibrary.library.service.interfaces.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable int id) {
        Optional<BookDto> bookOptional = bookService.getBookById(id);

        return bookOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Book with ID " + id + " not found"));
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<BookDto>> searchBook(@PathVariable String query) {
        List<BookDto> books = bookService.searchBooks(query);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByBookTitle/{title}")
    public ResponseEntity<List<BookDto>> searchBookByTitle(@Valid @PathVariable String title) {
        List<BookDto> books = bookService.searchBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByAuthorName/{name}")
    public ResponseEntity<List<BookDto>> searchBookByAuthorName(@Valid @PathVariable String name) {
        List<BookDto> books = bookService.searchBooksByAuthorName(name);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByGenre/{genre}")
    public ResponseEntity<List<BookDto>> searchBookByGenre(@Valid @PathVariable String genre) {
        List<BookDto> books = bookService.searchBooksByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByIsbnAndStatus/{isbn}/{status}")
    public ResponseEntity<BookDto> searchBookByIsbnAndStatus(@Valid @PathVariable String isbn, @Valid @PathVariable BookStatus status) {
        BookDto book = bookService.searchBookByIsbnAndStatus(isbn, status);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid @RequestBody BookDto bookDto) {
        Book book = bookService.saveBook(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDto> updateBookStatus(@PathVariable int id, @Valid @RequestBody BookStatus newStatus) {
        return bookService.updateBookStatus(id, newStatus)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Book with ID " + id + " and status " + newStatus + " not found."));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
