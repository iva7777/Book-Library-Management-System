package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class BookMapper {
    public BookDto mapEntityToDto(Book book) {
        if (book == null){
            return null;
        }

        Integer id = null;
        String title = null;
        String publisher = null;
        String isbn = null;
        String genre = null;
        BookStatus status = null;
        String authorNames = null;

        id = book.getId();
        title = book.getTitle();
        publisher = book.getPublisher();
        isbn = book.getIsbn();
        genre = book.getGenre();
        status = book.getStatus();

        authorNames = book.getAuthorBooks() == null || book.getAuthorBooks().isEmpty()
                ? "Unknown"
                : book.getAuthorBooks().stream()
                .map(authorBook -> authorBook.getAuthor().getFirstName() + " " + authorBook.getAuthor().getLastName())
                .collect(Collectors.joining(", "));

        return new BookDto(id, title, publisher, isbn, genre, status, authorNames);
    }

    public Book mapDtoToEntity(BookDto bookDto) {
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

    private List<Author> fetchAuthorsByNames(String authorNames) {
        if (authorNames == null || authorNames.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String[] names = authorNames.split(",\\s*");
        List<Author> authors = new ArrayList<>();

        for (String name : names) {
            String[] splitName = name.trim().split("\\s+");
            if (splitName.length >= 2) {
                String firstName = splitName[0];
                String lastName = splitName[1];

                Author author = new Author();
                author.setFirstName(firstName);
                author.setLastName(lastName);

                authors.add(author);
            }
        }

        return authors;
    }
}
