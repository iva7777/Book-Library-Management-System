package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.BookDto;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("application-test.properties")
@Transactional
class BookMapperTest {
    private final BookMapper bookMapper = new BookMapper();

    @Test
    void shouldCheckIfDtoIsNull() {
        BookDto bookDto = bookMapper.mapEntityToDto(null);

        Assertions.assertNull(bookDto);
    }

    @Test
    void shouldCheckIfEntityIsNull() {
        Book book = bookMapper.mapDtoToEntity(null);

        Assertions.assertNull(book);
    }

    @Test
    void shouldMapEntityToDto() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("test");
        book.setPublisher("test");
        book.setIsbn("test");
        book.setYearOfProduction(2003);
        book.setGenre("test");
        book.setDescription("test");
        book.setStatus(BookStatus.available);

        BookDto bookDto = bookMapper.mapEntityToDto(book);

        Assertions.assertNotNull(bookDto);
        Assertions.assertEquals(book.getId(), bookDto.id());
        Assertions.assertEquals(book.getTitle(), bookDto.title());
        Assertions.assertEquals(book.getPublisher(), bookDto.publisher());
        Assertions.assertEquals(book.getIsbn(), bookDto.isbn());
        Assertions.assertEquals(book.getGenre(), bookDto.genre());
        Assertions.assertEquals(book.getStatus(), bookDto.status());
    }

    @Test
    void shouldMapDtoToEntity() {
        BookDto bookDto = new BookDto(1, "test", "Doe", "isbn", "genre", BookStatus.available, "John Doe");

        Book book = bookMapper.mapDtoToEntity(bookDto);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(bookDto.id(), book.getId());
        Assertions.assertEquals(bookDto.title(), book.getTitle());
        Assertions.assertEquals(bookDto.publisher(), book.getPublisher());
        Assertions.assertEquals(bookDto.isbn(), book.getIsbn());
        Assertions.assertEquals(bookDto.genre(), book.getGenre());
        Assertions.assertEquals(bookDto.status(), book.getStatus());
    }
}