package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("SELECT a FROM Author a JOIN a.authorBooks ab JOIN ab.book b WHERE b.title = :title")
    List<Author> findAuthorsByBookTitle(String title);
}
