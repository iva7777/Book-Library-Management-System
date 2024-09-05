package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.mapper.ReaderCardMapper;
import com.example.booklibrary.library.model.ReaderCard;
import com.example.booklibrary.library.repository.AppUserRepository;
import com.example.booklibrary.library.repository.ReaderCardRepository;
import com.example.booklibrary.library.security.AuthenticationService;
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

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private AppUserRepository appUserRepository;

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
        readerCardService = new ReaderCardServiceImpl(readerCardRepository, authenticationService, appUserRepository, readerCardMapper);

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

        Optional<ReaderCardDto> newDtoOptional = readerCardService.getReaderCardById(1);

        Assertions.assertTrue(newDtoOptional.isPresent());
        ReaderCardDto newDto = newDtoOptional.get();
        Assertions.assertEquals(readerCardDto, newDto);
        verify(readerCardRepository).findById(1);
    }

    @Test
    void shouldNotGetReaderCardById() {
        when(readerCardRepository.findById(1)).thenReturn(Optional.empty());

        Optional<ReaderCardDto> newDtoOptional = readerCardService.getReaderCardById(1);

        Assertions.assertTrue(newDtoOptional.isEmpty());

        verify(readerCardRepository).findById(1);
    }

    @Test
    void shouldGetReaderCardByReaderId() {
        when(readerCardRepository.findByReaderId(1)).thenReturn(Optional.of(readerCard));

        Optional<ReaderCardDto> result = readerCardService.getReaderCardByReaderId(1);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(readerCardDto, result.get());

        verify(readerCardRepository).findByReaderId(1);
    }

    @Test
    void shouldNotGetOwnReaderCardWhenUserNotFound() {
        // Arrange
        String loggedUsername = "user123";

        when(authenticationService.getAuthenticatedUsername()).thenReturn(loggedUsername);
        when(appUserRepository.findByUsername(loggedUsername)).thenReturn(Optional.empty());

        Optional<ReaderCardDto> result = readerCardService.getOwnReaderCard();

        Assertions.assertTrue(result.isEmpty());
        verify(authenticationService).getAuthenticatedUsername();
        verify(appUserRepository).findByUsername(loggedUsername);
    }

    @Test
    void shouldUpdateReaderCard() {
        when(readerCardRepository.findById(1)).thenReturn(Optional.of(readerCard));
        when(readerCardRepository.save(readerCard)).thenReturn(readerCard);

        Optional<ReaderCardDto> updatedReaderCardOptional = readerCardService.updateReaderCard(1, readerCardDto);

        Assertions.assertTrue(updatedReaderCardOptional.isPresent());
        ReaderCardDto updatedReaderCard = updatedReaderCardOptional.get();
        Assertions.assertEquals(readerCardDto, updatedReaderCard);
        verify(readerCardRepository).findById(1);
        verify(readerCardRepository).save(readerCard);
    }

    @Test
    void shouldNotUpdateReaderCard() {
        when(readerCardRepository.findById(1)).thenReturn(Optional.empty());

        Optional<ReaderCardDto> updatedReaderCardOptional = readerCardService.updateReaderCard(1, readerCardDto);

        Assertions.assertTrue(updatedReaderCardOptional.isEmpty());
        verify(readerCardRepository).findById(1);
    }

    @Test
    void shouldDeleteReaderCard() {
        readerCardService.deleteReaderCard(1);

        verify(readerCardRepository).deleteById(1);
    }
}