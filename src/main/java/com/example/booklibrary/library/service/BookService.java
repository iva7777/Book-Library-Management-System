package com.example.booklibrary.library.service;

import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(int id){
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found."));
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

//    public void updateBookStatus(int bookId, boolean newStatus){
//        Optional<Book> optionalBook = bookRepository.findById(bookId);
//        if (optionalBook.isPresent()){
//            Book book = optionalBook.get();
//            book.setStatus(newStatus);
//            bookRepository.save(book);
//        } else {
//            throw new RuntimeException("Book with ID " + bookId + " not found.");
//        }
//    }

    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooksByTitle(String title){
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> searchBooksByAuthorName(String authorName){
        return bookRepository.findByAuthorNameContaining(authorName);
    }

    public List<Book> searchBooksByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }

    public List<Book> searchBooks(String query) {
        List<Book> booksByTitle = bookRepository.findByTitleContaining(query);
        List<Book> booksByAuthorName = bookRepository.findByAuthorNameContaining(query);
        List<Book> booksByGenre = bookRepository.findByGenre(query);

        booksByTitle.addAll(booksByAuthorName);
        booksByTitle.addAll(booksByGenre);

        return booksByTitle.stream().distinct().toList();
    }
}
