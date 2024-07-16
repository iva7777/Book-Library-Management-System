package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
