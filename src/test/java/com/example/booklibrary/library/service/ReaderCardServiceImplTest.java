package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.mapper.ReaderCardMapper;
import com.example.booklibrary.library.model.ReaderCard;
import com.example.booklibrary.library.repository.ReaderCardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReaderCardServiceImplTest {
    @Mock
    private ReaderCardRepository readerCardRepository;

    private ReaderCardMapper readerCardMapper;

    @InjectMocks
    private ReaderCardServiceImpl readerCardService;

    private ReaderCard readerCard;
    private ReaderCardDto readerCardDto;

    private Date rentDate;
    private Date returnDate;

    @BeforeEach
    void setUp() {
        readerCardMapper = new ReaderCardMapper();
        readerCardService = new ReaderCardServiceImpl(readerCardRepository, readerCardMapper);

        Calendar calendarRent = Calendar.getInstance();
        calendarRent.set(2024, Calendar.AUGUST, 15);
        rentDate = calendarRent.getTime();

        Calendar calendarReturn = Calendar.getInstance();
        calendarReturn.set(2024, Calendar.SEPTEMBER, 15);
        returnDate = calendarReturn.getTime();

        readerCard = new ReaderCard();
        readerCard.setId(1);
        readerCard.setRentDate(rentDate);
        readerCard.setReturnDate(returnDate);

        readerCardDto = new ReaderCardDto(1, rentDate, returnDate);
    }

    @Test
    void shouldGetAllReaderCards() {
        when(readerCardRepository.findAll()).thenReturn(new ArrayList<>(List.of(readerCard)));

        List<ReaderCardDto> readerCards = readerCardService.getAllReaderCards();

        Assertions.assertEquals(1, readerCards.size());
        Assertions.assertEquals(rentDate, readerCards.getFirst().rentDate());
        Assertions.assertEquals(returnDate, readerCards.getFirst().returnDate());
    }

    @Test
    void shouldGetReaderCardById() {
        when(readerCardRepository.findById(1)).thenReturn(Optional.of(readerCard));

        ReaderCardDto newDto = readerCardService.getReaderCardById(1);

        Assertions.assertEquals(readerCardDto, newDto);
        verify(readerCardRepository).findById(1);
    }

    @Test
    void shouldNotGetReaderCardById() {
        when(readerCardRepository.findById(1)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            readerCardService.getReaderCardById(1);
        });

        Assertions.assertEquals("Reader card with ID 1 not found.", exception.getMessage());
    }

    @Test
    void shouldUpdateReaderCard() {
        when(readerCardRepository.findById(1)).thenReturn(Optional.of(readerCard));
        when(readerCardRepository.save(readerCard)).thenReturn(readerCard);

        readerCardService.updateReaderCard(1, readerCardDto);

        verify(readerCardRepository).findById(1);
        verify(readerCardRepository).save(readerCard);
    }

    @Test
    void shouldNotUpdateReaderCard() {
        when(readerCardRepository.findById(1)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            readerCardService.updateReaderCard(1, readerCardDto);
        });

        Assertions.assertEquals("Reader card with ID 1 not found.", exception.getMessage());
    }

    @Test
    void shouldDeleteReaderCard() {
        readerCardService.deleteReaderCard(1);

        verify(readerCardRepository).deleteById(1);
    }
}