package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.model.Author;
import org.mapstruct.Mapper;

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

        id = author.getId();
        firstName = author.getFirstName();
        lastName = author.getLastName();
        bio = author.getBio();

        return new AuthorDto(id, firstName, lastName, bio);
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
}
