package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.ReaderCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderCardRepository extends JpaRepository<ReaderCard, Integer> {
    Optional<ReaderCard> findByReaderId(int readerId);
}
