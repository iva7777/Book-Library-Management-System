package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.model.Book;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class AuthorMapper {
    public AuthorDto mapEntityToDto(Author author){
        if (author == null){
            return null;
        }

        Integer id = null;
        String firstName = null;
        String lastName = null;
        String bio = null;
        String bookTitles = null;

        id = author.getId();
        firstName = author.getFirstName();
        lastName = author.getLastName();
        bio = author.getBio();

        bookTitles = author.getAuthorBooks() == null || author.getAuthorBooks().isEmpty()
                ? "No books" : author.getAuthorBooks().stream()
                .map(authorBook -> authorBook.getBook().getTitle())
                .collect(Collectors.joining(", "));

        return new AuthorDto(id, firstName, lastName, bio, bookTitles);
    }

    public Author mapDtoToEntity(AuthorDto authorDto){
        if (authorDto == null) {
            return null;
        }

        Author author = new Author();

        author.setId(authorDto.id());
        author.setFirstName(authorDto.firstName());
        author.setLastName(authorDto.lastName());
        author.setBio(authorDto.bio());

        return author;
    }

    private List<Book> fetchBooksByTitle(String bookTitles) {
        if (bookTitles == null || bookTitles.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String[] titles = bookTitles.split(",\\s*");
        List<Book> books = new ArrayList<>();

        for (String title : titles) {
            String[] splitTitle = title.trim().split("\\s+");

            if (!title.isEmpty()) {
                Book book = new Book();
                book.setTitle(title);

                books.add(book);
            }
        }
        return books;
    }
}
