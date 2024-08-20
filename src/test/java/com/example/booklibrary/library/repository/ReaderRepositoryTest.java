package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Reader;
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
class ReaderRepositoryTest {
    @Autowired
    ReaderRepository readerRepository;

    private Reader reader;

    @BeforeEach
    void setUp() {
        reader = new Reader();

        reader.setFirstName("Read");
        reader.setLastName("Test");
        reader.setPhone("0896540389");
        reader.setEmail("test@tets.com");
        reader.setAddress("test.");
    }

    @Test
    void shouldFindAllReaders() {
        readerRepository.save(reader);

        List<Reader> readerList = readerRepository.findAll();

        assertThat(readerList)
                .isNotEmpty()
                .hasSizeGreaterThan(1);

    }

    @Test
    void shouldFindReaderById() {
        reader = new Reader();

        reader.setFirstName("Read");
        reader.setLastName("Test");
        readerRepository.save(reader);

        Optional<Reader> result = readerRepository.findById(reader.getId());
        assertThat(result)
                .isPresent()
                .hasValueSatisfying(r -> {
                    assertThat(r.getId()).isEqualTo(reader.getId());
                    assertThat(r.getFirstName()).isEqualTo("Read");
                    assertThat(r.getLastName()).isEqualTo("Test");
                });
    }

    @Test
    void shouldCreateReader() {
        Reader saved = readerRepository.save(reader);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void shouldDeleteReader() {
        readerRepository.save(reader);
        readerRepository.delete(reader);
        Optional<Reader> deleted = readerRepository.findById(reader.getId());
        assertThat(deleted).isEmpty();
    }

    @Test
    void shouldFindReaderByNameContaining() {
        Reader reader1 = new Reader();
        reader1.setFirstName("John");
        reader1.setLastName("Mith");
        readerRepository.save(reader1);

        Reader reader2 = new Reader();
        reader2.setFirstName("Johnny");
        reader2.setLastName("Doe");
        readerRepository.save(reader2);

        List<Reader> result = readerRepository.findReaderByNameContaining("John");


        assertThat(result)
                .isNotEmpty()
                .hasSize(2)
                .extracting(reader -> reader.getFirstName() + " " + reader.getLastName())
                .containsExactlyInAnyOrder("John Mith", "Johnny Doe");
    }

    @Test
    void shouldFindReaderByPhone() {
        reader.setPhone("123-456-7890");
        readerRepository.save(reader);

        Optional<Reader> result = readerRepository.findReaderByPhone("123-456-7890");

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(r -> assertThat(r.getPhone()).isEqualTo("123-456-7890"));
    }

    @Test
    void shouldFindReaderByEmail() {
        reader.setEmail("test@test.com");
        readerRepository.save(reader);

        Optional<Reader> result = readerRepository.findReaderByEmail("test@test.com");

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(r -> assertThat(r.getEmail()).isEqualTo("test@test.com"));
    }
}