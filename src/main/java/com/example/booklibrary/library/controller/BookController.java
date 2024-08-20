package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.exception.ApiResponse;
import com.example.booklibrary.library.exception.ResponseHelper;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
import com.example.booklibrary.library.service.interfaces.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<ApiResponse<BookDto>> getBookById(@PathVariable int id) {
        Optional<BookDto> bookOptional = bookService.getBookById(id);

        return bookOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Book with ID " + id + " not found"));
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<?> searchBook(@PathVariable String query) {
        List<BookDto> books = bookService.searchBooks(query);
        if (books.isEmpty()) {
            return ResponseHelper.failureResponse("No books found for the given search query.");
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByBookTitle/{title}")
    public ResponseEntity<?> searchBookByTitle(@Valid @PathVariable String title) {
        List<BookDto> books = bookService.searchBooksByTitle(title);
        if (books.isEmpty()) {
            return ResponseHelper.failureResponse("No books found for the given title.");
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByAuthorName/{name}")
    public ResponseEntity<?> searchBookByAuthorName(@Valid @PathVariable String name) {
        List<BookDto> books = bookService.searchBooksByAuthorName(name);
        if (books.isEmpty()) {
            return ResponseHelper.failureResponse("No books found for the given author name.");
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/searchByGenre/{genre}")
    public ResponseEntity<?> searchBookByGenre(@Valid @PathVariable String genre) {
        List<BookDto> books = bookService.searchBooksByGenre(genre);
        if (books.isEmpty()) {
            return ResponseHelper.failureResponse("No books found for the given genre.");
        }

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
    public ResponseEntity<ApiResponse<BookDto>> updateBookStatus(@PathVariable int id, @Valid @RequestBody BookStatus newStatus) {
        Optional<BookDto> updatedBook = bookService.updateBookStatus(id, newStatus);

        return updatedBook
                .map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Book with ID " + id + " and status " + newStatus + " not found."));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
