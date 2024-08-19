package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.mapper.AuthorMapper;
import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    AuthorRepository authorRepository;

    AuthorMapper authorMapper;

    @InjectMocks
    AuthorServiceImpl authorService;

    private Author author;
    private AuthorDto authorDto;

    @BeforeEach
    void setUp() {
        authorMapper = new AuthorMapper();
        authorService = new AuthorServiceImpl(authorRepository, authorMapper);

        author = new Author();
        author.setId(1);
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setBio("just bio");

        authorDto = new AuthorDto(1, "John", "Doe", "just bio");
    }

    @Test
    void shouldGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(new ArrayList<>(List.of(author)));

        String authorFirstName = "John";
        String authorLastName = "Doe";

        List<AuthorDto> authors = authorService.getAllAuthors();

        Assertions.assertEquals(1, authors.size());
        Assertions.assertEquals(authorFirstName, authors.getFirst().firstName());
        Assertions.assertEquals(authorLastName, authors.getFirst().lastName());
    }

    @Test
    void shouldGetAuthorById() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        Optional<AuthorDto> newDtoOptional = authorService.getAuthorById(1);

        Assertions.assertTrue(newDtoOptional.isPresent());
        AuthorDto newDto = newDtoOptional.get();
        Assertions.assertEquals(authorDto, newDto);
        verify(authorRepository).findById(1);
    }

    @Test
    void shouldNotGetAuthorById() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());

        Optional<AuthorDto> newDtoOptional = authorService.getAuthorById(1);

        Assertions.assertTrue(newDtoOptional.isEmpty());

        verify(authorRepository).findById(1);
    }

    @Test
    void shouldUpdateAuthorInfo() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(authorRepository.save(author)).thenReturn(author);

        Optional<AuthorDto> updatedAuthorOptional = authorService.updateAuthorInfo(1, authorDto);

        Assertions.assertTrue(updatedAuthorOptional.isPresent());
        AuthorDto updatedAuthor = updatedAuthorOptional.get();
        Assertions.assertEquals(authorDto, updatedAuthor);
        verify(authorRepository).findById(1);
        verify(authorRepository).save(author);
    }

    @Test
    void shouldNotUpdateAuthorInfo() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());

        Optional<AuthorDto> updatedAuthorOptional = authorService.updateAuthorInfo(1, authorDto);

        Assertions.assertTrue(updatedAuthorOptional.isEmpty());
        verify(authorRepository).findById(1);
    }


    @Test
    void shouldDeleteAuthor() {
        authorService.deleteAuthor(2);

        verify(authorRepository).deleteById(2);
    }

    @Test
    void shouldSearchAuthorByBookAndReturnOneAuthor() {
        String bookTitle = "test";
        String authorFirstName = "John";
        String authorLastName = "Doe";

        when(authorRepository.findAuthorsByBookTitle(bookTitle)).thenReturn(new ArrayList<>(List.of(author)));

        List<AuthorDto> authors = authorService.searchAuthorByBook(bookTitle);

        Assertions.assertEquals(1, authors.size());
        Assertions.assertEquals(authorFirstName, authors.getFirst().firstName());
        Assertions.assertEquals(authorLastName, authors.getFirst().lastName());
    }

    @Test
    void shouldSearchAuthorByBookAndReturnEmptyList() {
        when(authorRepository.findAuthorsByBookTitle(anyString())).thenReturn(new ArrayList<>());

        List<AuthorDto> authors = authorService.searchAuthorByBook("title");

        Assertions.assertEquals(0, authors.size());
    }
}