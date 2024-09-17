package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.AuthorBook;
import com.example.booklibrary.library.model.AuthorBookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, AuthorBookId> {
}
