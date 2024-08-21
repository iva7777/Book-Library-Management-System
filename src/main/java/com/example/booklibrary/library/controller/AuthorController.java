package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.common.response.ApiResponse;
import com.example.booklibrary.library.common.util.ResponseHelper;
import com.example.booklibrary.library.dto.AuthorDto;
import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.service.interfaces.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> searchAuthorsByBookTitle(@Valid @PathVariable String title) {
        List<AuthorDto> authors = authorService.searchAuthorByBook(title);

        if (authors.isEmpty()) {
            return ResponseHelper.notFoundResponse("No authors found for the given book title.");
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<AuthorDto>> getAuthorById(@PathVariable int id) {
        Optional<AuthorDto> authorOptional = authorService.getAuthorById(id);

        return authorOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Author with ID " + id + " not found"));
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@Valid @RequestBody AuthorDto authorDto) {
        Author author = authorService.saveAuthor(authorDto);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<AuthorDto>> updateAuthor(@PathVariable int id, @Valid @RequestBody AuthorDto authorDto) {
        Optional<AuthorDto> authorOptional = authorService.updateAuthorInfo(id, authorDto);

        return authorOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Author with ID " + id + " not found"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
