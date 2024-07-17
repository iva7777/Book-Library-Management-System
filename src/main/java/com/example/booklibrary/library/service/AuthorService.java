package com.example.booklibrary.library.service;

import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id){
        return authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author not found."));
    }

    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }

    public void updateAuthorInfo(int authorId, Author authorDetails){
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()){
            Author author = optionalAuthor.get();
            author.setFirstName(authorDetails.getFirstName());
            author.setLastName(authorDetails.getLastName());
            author.setBio(authorDetails.getBio());
            author.setAuthorBooks(authorDetails.getAuthorBooks());
        } else {
            throw new RuntimeException("Author with ID " + authorId + " not found.");
        }
    }

    public void deleteAuthor(int id){
        authorRepository.deleteById(id);
    }

    public List<Author> searchAuthorByBook(String title){
        return authorRepository.findAuthorsByBookTitle(title);
    }
}
