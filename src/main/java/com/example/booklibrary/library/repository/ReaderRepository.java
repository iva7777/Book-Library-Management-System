package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    @Query("SELECT r FROM Reader r WHERE r.firstName LIKE %:name% OR r.lastName LIKE %:name% OR CONCAT(r.firstName, ' ', r.lastName) LIKE %:name%")
    List<Reader> findReaderByNameContaining(@Param("name") String name);

    Reader findReaderByPhone(String phone);
    Reader findReaderByEmail(String email);
}
