package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.service.interfaces.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/searchByBookTitle/{title}")
    public ResponseEntity<List<AuthorDto>> searchAuthorsByBookTitle(@PathVariable String title) {
        List<AuthorDto> authors = authorService.searchAuthorByBook(title);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable int id) {
        try {
            AuthorDto author = authorService.getAuthorById(id);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorService.saveAuthor(authorDto);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable int id, @RequestBody AuthorDto authorDto) {
        try {
            authorService.updateAuthorInfo(id, authorDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
