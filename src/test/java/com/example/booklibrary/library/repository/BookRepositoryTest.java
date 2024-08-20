package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
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
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1);
        book.setTitle("test");
        book.setIsbn("000-02544");
        book.setPublisher("test");
        book.setYearOfProduction(2003);
        book.setGenre("drama");
        book.setDescription("test");
        book.setStatus(BookStatus.borrowed);
    }

    @Test
    void shouldFindAllBooks() {
        bookRepository.save(book);

        List<Book> bookList = bookRepository.findAll();

        assertThat(bookList)
                .isNotEmpty()
                .hasSizeGreaterThan(1);

    }

    @Test
    void shouldFindBookById() {
        bookRepository.save(book);
        Optional<Book> result = bookRepository.findById(book.getId());
        assertThat(result)
                .isPresent()
                .hasValueSatisfying(b -> assertThat(b.getId()).isEqualTo(book.getId()));
    }

    @Test
    void shouldCreateBook() {
        Book savedBook = bookRepository.save(book);
        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    void shouldDeleteBook() {
        bookRepository.save(book);
        bookRepository.delete(book);
        Optional<Book> deleted = bookRepository.findById(book.getId());
        assertThat(deleted).isEmpty();
    }

    @Test
    void shouldFindByTitleContaining() {
        Book book1 = new Book();
        book1.setTitle("Spring Framework Basics");
        book1.setStatus(BookStatus.available);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Spring Boot in Action");
        book2.setStatus(BookStatus.available);
        bookRepository.save(book2);

        List<Book> result = bookRepository.findByTitleContaining("Spring");
        assertThat(result)
                .isNotEmpty()
                .hasSize(2)  // Expecting 2 books that contain "Spring" in the title
                .extracting(Book::getTitle)
                .containsExactlyInAnyOrder("Spring Framework Basics", "Spring Boot in Action");
    }

    @Test
    void shouldFindByGenre() {
        Book book1 = new Book();
        book1.setTitle("Book Title 1");
        book1.setGenre("Science Fiction");
        book1.setStatus(BookStatus.available);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Book Title 2");
        book2.setGenre("Science Fiction");
        book2.setStatus(BookStatus.available);
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setTitle("Book Title 3");
        book3.setGenre("Fantasy");
        book3.setStatus(BookStatus.available);
        bookRepository.save(book3);

        List<Book> result = bookRepository.findByGenre("Science Fiction");

        assertThat(result)
                .isNotEmpty()
                .hasSize(2)
                .extracting(Book::getTitle)
                .containsExactlyInAnyOrder("Book Title 1", "Book Title 2");
    }

    @Test
    void findBookByIsbnAndStatus() {
        Book book1 = new Book();
        book1.setTitle("Book Title 1");
        book1.setIsbn("123456780");
        book1.setStatus(BookStatus.available);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Book Title 2");
        book2.setIsbn("1234567890");
        book2.setStatus(BookStatus.available);
        bookRepository.save(book2);

        Optional<Book> result = bookRepository.findBookByIsbnAndStatus("1234567890", BookStatus.available);

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(book -> {
                    assertThat(book.getIsbn()).isEqualTo("1234567890");
                    assertThat(book.getStatus()).isEqualTo(BookStatus.available);
                });
    }
}