package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.model.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("application-test.properties")
@Transactional
class AuthorMapperTest {
    private final AuthorMapper authorMapper = new AuthorMapper();

    @Test
    void shouldCheckIfDtoIsNull() {
        AuthorDto authorDto = authorMapper.mapEntityToDto(null);

        Assertions.assertNull(authorDto);
    }

    @Test
    void shouldCheckIfEntityIsNull() {
        Author author = authorMapper.mapDtoToEntity(null);

        Assertions.assertNull(author);
    }

    @Test
    void shouldMapEntityToDto() {
        Author author = new Author();
        author.setId(1);
        author.setFirstName("test");
        author.setLastName("test");
        author.setBio("test");

        AuthorDto authorDto = authorMapper.mapEntityToDto(author);

        Assertions.assertNotNull(authorDto);
        Assertions.assertEquals(author.getId(), authorDto.id());
        Assertions.assertEquals(author.getFirstName(), authorDto.firstName());
        Assertions.assertEquals(author.getLastName(), authorDto.lastName());
        Assertions.assertEquals(author.getBio(), authorDto.bio());
    }

    @Test
    void shouldMapDtoToEntity() {
        AuthorDto authorDto = new AuthorDto(1, "John", "Doe", "Author bio here", "Books");

        Author author = authorMapper.mapDtoToEntity(authorDto);

        Assertions.assertNotNull(author);
        Assertions.assertEquals(authorDto.id(), author.getId());
        Assertions.assertEquals(authorDto.firstName(), author.getFirstName());
        Assertions.assertEquals(authorDto.lastName(), author.getLastName());
        Assertions.assertEquals(authorDto.bio(), author.getBio());
    }
}