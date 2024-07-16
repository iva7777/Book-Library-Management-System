package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
}
