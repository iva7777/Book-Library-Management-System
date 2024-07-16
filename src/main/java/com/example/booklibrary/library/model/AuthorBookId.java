package com.example.booklibrary.library.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class AuthorBookId implements Serializable {

    @ManyToOne
    private Author authorId;

    @ManyToOne
    private Book bookId;
    public AuthorBookId(){}
    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }
}
