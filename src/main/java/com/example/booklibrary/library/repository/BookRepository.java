package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Book;
import com.example.booklibrary.library.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContaining(String title);
    @Query("SELECT b FROM Book b JOIN b.authorBooks ab JOIN ab.author a WHERE a.firstName LIKE %:name% OR a.lastName LIKE %:name% OR CONCAT(a.firstName, ' ', a.lastName) LIKE %:name%")
    List<Book> findByAuthorNameContaining(@Param("name") String name);
    List<Book> findByGenre(String genre);

    Optional<Book> findBookByIsbnAndStatus(String isbn, BookStatus status);
}
