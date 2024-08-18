package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.mapper.ReaderMapper;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.model.ReaderCard;
import com.example.booklibrary.library.repository.ReaderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReaderServiceImplTest {

    @Mock
    private ReaderRepository readerRepository;

    private ReaderMapper readerMapper;

    @InjectMocks
    private ReaderServiceImpl readerService;

    private Reader reader;
    private ReaderDto readerDto;

    private ReaderCard readerCard;
    private ReaderCardDto readerCardDto;

    private Date rentDate;
    private Date returnDate;

    @BeforeEach
    void setUp() {
        readerMapper = new ReaderMapper();
        readerService = new ReaderServiceImpl(readerRepository, readerMapper);

        Calendar calendarRent = Calendar.getInstance();
        calendarRent.set(2024, Calendar.AUGUST, 15);
        rentDate = calendarRent.getTime();

        Calendar calendarReturn = Calendar.getInstance();
        calendarReturn.set(2024, Calendar.SEPTEMBER, 15);
        returnDate = calendarReturn.getTime();

        reader = new Reader();
        reader.setId(1);
        reader.setFirstName("John");
        reader.setLastName("Doe");
        reader.setPhone("089564712");
        reader.setAddress("test");
        reader.setEmail("test@test.com");

        readerCard = new ReaderCard();
        readerCard.setId(1);
        readerCard.setRentDate(rentDate);
        readerCard.setReturnDate(returnDate);

        reader.setReaderCard(readerCard);
        readerCardDto = new ReaderCardDto(1, rentDate, returnDate);

        readerDto = new ReaderDto(1, "John", "Doe", "089564712", "test", "test@test.com", readerCardDto);
    }

    @Test
    void shouldGetAllReaders() {
        when(readerRepository.findAll()).thenReturn(new ArrayList<>(List.of(reader)));

        String firstName = "John";
        String lastName = "Doe";
        String phone = "089564712";
        String address = "test";
        String email = "test@test.com";

        List<ReaderDto> readers = readerService.getAllReaders();

        Assertions.assertEquals(1, readers.size());
        Assertions.assertEquals(firstName, readers.getFirst().firstName());
        Assertions.assertEquals(lastName, readers.getFirst().lastName());
        Assertions.assertEquals(phone, readers.getFirst().phone());
        Assertions.assertEquals(address, readers.getFirst().address());
        Assertions.assertEquals(email, readers.getFirst().email());
    }

    @Test
    void shouldGetReaderById() {
        when(readerRepository.findById(1)).thenReturn(Optional.of(reader));

        ReaderDto newDto = readerService.getReaderById(1);

        Assertions.assertEquals(readerDto, newDto);
        verify(readerRepository).findById(1);
    }

    @Test
    void shouldNotGetReaderById() {
        when(readerRepository.findById(1)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            readerService.getReaderById(1);
        });

        Assertions.assertEquals("Reader with ID 1 not found.", exception.getMessage());
    }

    @Test
    void shouldUpdateReaderInfo() {
        when(readerRepository.findById(1)).thenReturn(Optional.of(reader));
        when(readerRepository.save(reader)).thenReturn(reader);

        readerService.updateReaderInfo(1, readerDto);

        verify(readerRepository).findById(1);
        verify(readerRepository).save(reader);
    }

    @Test
    void shouldNotUpdateReaderInfo() {
        when(readerRepository.findById(1)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            readerService.updateReaderInfo(1, readerDto);
        });

        Assertions.assertEquals("Reader with ID 1 not found.", exception.getMessage());
    }

    @Test
    void shouldDeleteReader() {
        readerService.deleteReader(1);

        verify(readerRepository).deleteById(1);
    }

    @Test
    void shouldSearchReaderByName() {
        String name = "John Doe";

        when(readerRepository.findReaderByNameContaining(name)).thenReturn(new ArrayList<>(List.of(reader)));

        List<ReaderDto> readers = readerService.searchReaderByName(name);

        Assertions.assertEquals(1, readers.size());
        Assertions.assertEquals("John", readers.getFirst().firstName());
        Assertions.assertEquals("Doe", readers.getFirst().lastName());
    }

    @Test
    void shouldSearchReaderByNameAndReturnEmptyList() {
        when(readerRepository.findReaderByNameContaining(anyString())).thenReturn(new ArrayList<>());

        List<ReaderDto> readers = readerService.searchReaderByName("test");

        Assertions.assertEquals(0, readers.size());
    }

    @Test
    void shouldSearchReaderByPhoneNumber() {
        String phone = "089564712";

        when(readerRepository.findReaderByPhone(phone)).thenReturn(Optional.of(reader));

        readerDto = readerService.searchReaderByPhoneNumber(phone);

        Assertions.assertNotNull(readerDto);
        verify(readerRepository).findReaderByPhone(phone);
    }

    @Test
    void shouldSearchReaderByPhoneAndReturnOptionalEmpty() {
        String phone = "1234567890";

        when(readerRepository.findReaderByPhone(phone)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            readerService.searchReaderByPhoneNumber(phone);
        });

        Assertions.assertEquals("Reader with phone " + phone + " not found.", exception.getMessage());
    }

    @Test
    void shouldSearchReaderByEmail() {
        String email = "test@test.com";

        when(readerRepository.findReaderByEmail(email)).thenReturn(Optional.of(reader));

        readerDto = readerService.searchReaderByEmail(email);

        Assertions.assertNotNull(readerDto);
        verify(readerRepository).findReaderByEmail(email);
    }

    @Test
    void shouldSearchReaderByEmailAndReturnOptionalEmpty() {
        String email = "test@test.com";

        when(readerRepository.findReaderByEmail(email)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            readerService.searchReaderByEmail(email);
        });

        Assertions.assertEquals("Reader with email " + email + " not found.", exception.getMessage());
    }
}