package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();

    Optional<AuthorDto> getAuthorById(int id);

    Author saveAuthor(AuthorDto authorDto);

    Optional<AuthorDto> updateAuthorInfo(int authorId, AuthorDto authorDetailsDto);

    void deleteAuthor(int id);

    List<AuthorDto> searchAuthorByBook(String title);
}
