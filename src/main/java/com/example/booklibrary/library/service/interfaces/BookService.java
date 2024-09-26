package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
import com.example.booklibrary.library.search.BookSearchRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();

    Optional<BookDto> getBookById(int id);

    Book saveBook(BookDto bookDto);

    Optional<BookDto> updateBookStatus(int bookId, BookStatus newStatus);

    void deleteBook(int id);

    List<BookDto> searchBooksByTitle(String title);

    List<BookDto> searchBooksByAuthorName(String authorName);

    List<BookDto> searchBooksByGenre(String genre);

    List<BookDto> searchBooks(String query);

    BookDto searchBookByIsbnAndStatus(String isbn, BookStatus status);

    List<BookDto> findBooksByCriteria(BookSearchRequest request);
}
