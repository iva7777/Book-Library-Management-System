package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.mapper.AuthorMapper;
import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.repository.AuthorRepository;
import com.example.booklibrary.library.service.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
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

    public AuthorDto getAuthorById(int id) {
        var author = authorRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author with ID " + id + "not found."));

        return authorMapper.mapEntityToDto(author);
    }

    public Author saveAuthor(AuthorDto authorDto) {
        Author author = authorMapper.mapDtoToEntity(authorDto);

        return authorRepository.save(author);
    }

    public void updateAuthorInfo(int authorId, AuthorDto authorDetailsDto) {
        Author author = authorRepository
                .findById(authorId)
                .orElseThrow(() -> new NoSuchElementException("Author with ID " + authorId + "not found."));

        Author authorDetails = authorMapper.mapDtoToEntity(authorDetailsDto);

        author.setFirstName(authorDetails.getFirstName());
        author.setLastName(authorDetails.getLastName());
        author.setBio(authorDetails.getBio());
        author.setAuthorBooks(authorDetails.getAuthorBooks());

        authorRepository.save(author);
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
