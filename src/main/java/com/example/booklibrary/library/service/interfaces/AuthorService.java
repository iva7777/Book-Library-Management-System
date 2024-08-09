package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.model.Author;

import java.util.List;
import java.util.NoSuchElementException;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();

    AuthorDto getAuthorById(int id) throws NoSuchElementException;

    Author saveAuthor(AuthorDto authorDto);

    void updateAuthorInfo(int authorId, AuthorDto authorDetailsDto) throws NoSuchElementException;

    void deleteAuthor(int id);

    List<AuthorDto> searchAuthorByBook(String title);
}
