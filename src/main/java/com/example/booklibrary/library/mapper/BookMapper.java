package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;

public class BookMapper {
    public BookDto mapEntityToDto(Book book){
        if (book == null){
            return null;
        }

        Integer id = null;
        String title = null;
        String publisher = null;
        String isbn = null;
        String genre = null;
        BookStatus status = null;

        id = book.getId();
        title = book.getTitle();
        publisher = book.getPublisher();
        isbn = book.getIsbn();
        genre = book.getGenre();
        status = book.getStatus();

        return new BookDto(id, title, publisher, isbn, genre, status);
    }

    public Book mapDtoToEntity(BookDto bookDto){
        if (bookDto == null) {
            return null;
        }

        Book book = new Book();

        book.setId(bookDto.id());
        book.setTitle(bookDto.title());
        book.setPublisher(bookDto.publisher());
        book.setIsbn(bookDto.isbn());
        book.setGenre(bookDto.genre());
        book.setStatus(bookDto.status());

        return book;
    }
}
