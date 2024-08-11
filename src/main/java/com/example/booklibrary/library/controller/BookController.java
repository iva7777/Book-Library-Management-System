package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
import com.example.booklibrary.library.service.interfaces.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            BookDto book = bookService.getBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<BookDto>> searchBook(@PathVariable String query) {
        List<BookDto> books = bookService.searchBooks(query);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByBookTitle/{title}")
    public ResponseEntity<List<BookDto>> searchBookByTitle(@PathVariable String title) {
        List<BookDto> books = bookService.searchBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByAuthorName/{name}")
    public ResponseEntity<List<BookDto>> searchBookByAuthorName(@PathVariable String name) {
        List<BookDto> books = bookService.searchBooksByAuthorName(name);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByGenre/{genre}")
    public ResponseEntity<List<BookDto>> searchBookByGenre(@PathVariable String genre) {
        List<BookDto> books = bookService.searchBooksByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByIsbnAndStatus/{isbn}/{status}")
    public ResponseEntity<BookDto> searchBookByIsbnAndStatus(@PathVariable String isbn, @PathVariable BookStatus status) {
        BookDto book = bookService.searchBookByIsbnAndStatus(isbn, status);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> saveAuthor(@RequestBody BookDto bookDto) {
        Book book = bookService.saveBook(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateBookStatus(@PathVariable int id, @RequestBody BookStatus newStatus) {
        try {
            bookService.updateBookStatus(id, newStatus);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
