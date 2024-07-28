package com.example.booklibrary.library.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30)
    private String title;
    @Column(length = 20)
    private String publisher;
    @Column(length = 15)
    private String isbn;
    @Column(length = 4)
    private Integer yearOfProduction;
    @Column(length = 20)
    private String genre;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Boolean status;

    @OneToMany(mappedBy = "book")
    private List<ReaderCard> readerCards;

    @OneToMany(mappedBy = "book")
    private List<AuthorBook> authorBooks;

    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }



    public List<ReaderCard> getReaderCards() {
        return readerCards;
    }

    public void setReaderCards(List<ReaderCard> readerCards) {
        this.readerCards = readerCards;
    }

    public List<AuthorBook> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(List<AuthorBook> authorBooks) {
        this.authorBooks = authorBooks;
    }
}
