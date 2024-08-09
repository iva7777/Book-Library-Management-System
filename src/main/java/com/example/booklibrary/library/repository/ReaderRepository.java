package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    @Query("SELECT r FROM Reader r WHERE r.firstName LIKE %:name% OR r.lastName LIKE %:name% OR CONCAT(r.firstName, ' ', r.lastName) LIKE %:name%")
    List<Reader> findReaderByNameContaining(@Param("name") String name);

    Optional<Reader> findReaderByPhone(String phone);

    Optional<Reader> findReaderByEmail(String email);
}
