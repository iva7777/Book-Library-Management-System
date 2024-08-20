package com.example.booklibrary.library.repository;

import com.example.booklibrary.library.model.ReaderCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@ActiveProfiles("application-test.properties")
@SpringBootTest
@Transactional
class ReaderCardRepositoryTest {
    @Autowired
    ReaderCardRepository readerCardRepository;

    private ReaderCard readerCard;

    private Date rentDate;
    private Date returnDate;

    @BeforeEach
    void setUp() {
        readerCard = new ReaderCard();

        Calendar calendarRent = Calendar.getInstance();
        calendarRent.set(2024, Calendar.AUGUST, 15);
        rentDate = calendarRent.getTime();

        Calendar calendarReturn = Calendar.getInstance();
        calendarReturn.set(2024, Calendar.SEPTEMBER, 15);
        returnDate = calendarReturn.getTime();

        readerCard.setId(1);
        readerCard.setRentDate(rentDate);
        readerCard.setReturnDate(returnDate);
    }

    @Test
    void shouldFindAllReaderCards() {
        readerCardRepository.save(readerCard);

        List<ReaderCard> readerCardList = readerCardRepository.findAll();

        assertThat(readerCardList)
                .isNotEmpty()
                .hasSizeGreaterThan(1);

    }

    @Test
    void shouldFindReaderCardById() {
        ReaderCard readerCard = new ReaderCard();
        readerCard.setRentDate(rentDate);
        readerCard.setReturnDate(returnDate);
        readerCardRepository.save(readerCard);

        Optional<ReaderCard> result = readerCardRepository.findById(readerCard.getId());
        assertThat(result)
                .isPresent()
                .hasValueSatisfying(rc -> assertThat(rc.getId()).isEqualTo(readerCard.getId()));
    }

    @Test
    void shouldCreateReaderCard() {
        ReaderCard saved = readerCardRepository.save(readerCard);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void shouldDeleteReaderCard() {
        readerCardRepository.save(readerCard);
        readerCardRepository.delete(readerCard);
        Optional<ReaderCard> deleted = readerCardRepository.findById(readerCard.getId());
        assertThat(deleted).isEmpty();
    }
}