package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
