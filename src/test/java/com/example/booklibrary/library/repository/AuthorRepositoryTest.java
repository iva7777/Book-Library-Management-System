package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ActiveProfiles("application-test.properties")
@SpringBootTest
@Transactional
class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1);
        author.setFirstName("test");
        author.setLastName("test");
        author.setBio("test");
    }

    @Test
    void shouldFindAllAuthors() {
        authorRepository.save(author);

        List<Author> authorList = authorRepository.findAll();

        assertThat(authorList)
                .isNotEmpty()
                .hasSizeGreaterThan(1);

    }

    @Test
    void shouldFindAuthorById() {
        Author author = new Author();
        author.setFirstName("Author");
        author.setLastName("Test");
        authorRepository.save(author);
        Optional<Author> result = authorRepository.findById(author.getId());
        assertThat(result)
                .isPresent()
                .hasValueSatisfying(a -> {
                    assertThat(a.getId()).isEqualTo(author.getId());
                    assertThat(a.getFirstName()).isEqualTo("Author");
                    assertThat(a.getLastName()).isEqualTo("Test");
                });
    }

    @Test
    void shouldCreateAuthor() {
        Author savedAuthor = authorRepository.save(author);
        assertThat(savedAuthor.getId()).isNotNull();
    }

    @Test
    void shouldDeleteAuthor() {
        authorRepository.save(author);
        authorRepository.delete(author);
        Optional<Author> deleted = authorRepository.findById(author.getId());
        assertThat(deleted).isEmpty();
    }
}