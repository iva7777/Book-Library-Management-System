package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;

import java.util.List;
import java.util.NoSuchElementException;

public interface BookService {
    List<BookDto> getAllBooks();

    BookDto getBookById(int id) throws NoSuchElementException;

    Book saveBook(BookDto bookDto);

    void updateBookStatus(int bookId, BookStatus newStatus) throws NoSuchElementException;

    void deleteBook(int id);

    List<BookDto> searchBooksByTitle(String title);

    List<BookDto> searchBooksByAuthorName(String authorName);

    List<BookDto> searchBooksByGenre(String genre);

    List<BookDto> searchBooks(String query);

    BookDto searchBookByIsbnAndStatus(String isbn, BookStatus status);
}
