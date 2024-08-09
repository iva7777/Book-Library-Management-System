package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.ReaderCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderCardRepository extends JpaRepository<ReaderCard, Integer> {
}
