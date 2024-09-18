package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.mapper.AuthorMapper;
import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.repository.AuthorRepository;
import com.example.booklibrary.library.service.interfaces.AuthorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(
            @NotNull AuthorRepository authorRepository,
            @NotNull AuthorMapper authorMapper
    ) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDto> getAllAuthors() {

        return authorRepository
                .findAll()
                .stream()
                .map(authorMapper::mapEntityToDto)
                .toList();
    }

    public Optional<AuthorDto> getAuthorById(int id) {
        return authorRepository.findById(id)
                .map(authorMapper::mapEntityToDto);
    }

    public Author saveAuthor(AuthorDto authorDto) {
        Author author = authorMapper.mapDtoToEntity(authorDto);

        return authorRepository.save(author);
    }

    public Optional<AuthorDto> updateAuthorInfo(int authorId, AuthorDto authorDetailsDto) {
        return authorRepository.findById(authorId).map(existingAuthor -> {
            existingAuthor.setFirstName(authorDetailsDto.firstName());
            existingAuthor.setLastName(authorDetailsDto.lastName());
            existingAuthor.setBio(authorDetailsDto.bio());
            Author updatedAuthor = authorRepository.save(existingAuthor);
            return authorMapper.mapEntityToDto(updatedAuthor);
        });
    }

    public void deleteAuthor(int id){
        authorRepository.deleteById(id);
    }

    public List<AuthorDto> searchAuthorByBook(String title) {
        return authorRepository
                .findAuthorsByBookTitle(title)
                .stream()
                .map(authorMapper::mapEntityToDto)
                .toList();
    }
}
