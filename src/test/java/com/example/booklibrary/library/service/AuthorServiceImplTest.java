package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.mapper.AuthorMapper;
import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
class AuthorServiceImplTest {

    @Mock
    AuthorRepository authorRepository;
    AuthorMapper mapper;
    AuthorServiceImpl authorService;

    @BeforeEach
    void setUp() {
        mapper = new AuthorMapper();
        authorService = new AuthorServiceImpl(authorRepository, mapper);

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        when(authorRepository.findAll()).thenReturn(new ArrayList<>(List.of(author)));
    }

    @Test
    void shouldGetAllAuthors() {
        String authorFirstName = "John";
        String authorLastName = "Doe";

        List<AuthorDto> authors = authorService.getAllAuthors();

        Assertions.assertEquals(1, authors.size());
        Assertions.assertEquals(authorFirstName, authors.getFirst().firstName());
        Assertions.assertEquals(authorLastName, authors.getFirst().lastName());
    }


}