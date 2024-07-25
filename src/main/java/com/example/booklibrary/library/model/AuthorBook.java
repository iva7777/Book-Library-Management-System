package com.example.booklibrary.library.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AuthorBook {

    @EmbeddedId
    private AuthorBookId id;

    @ManyToOne
    @MapsId("authorId")
    //@JoinColumn(name = "authorId")
    private Author author;

    @ManyToOne
    @MapsId("bookId")
    //@JoinColumn(name = "bookId")
    private Book book;
    private Date creationDate;

    public AuthorBook(){}


    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public AuthorBookId getId() {
        return id;
    }

    public void setId(AuthorBookId id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Transient
    public Author getAuthor() {
        return this.id.getAuthorId();
    }

    @Transient
    public Book getBook() {
        return this.id.getBookId();
    }
}
