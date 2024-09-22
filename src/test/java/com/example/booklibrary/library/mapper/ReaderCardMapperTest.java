package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.model.ReaderCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@ActiveProfiles("application-test.properties")
@Transactional
class ReaderCardMapperTest {
    private final ReaderCardMapper readerCardMapper = new ReaderCardMapper();

    private Date rentDate;
    private Date returnDate;
    private String readerNames;
    private Reader reader;

    @BeforeEach
    public void setup() {
        Calendar calendarRent = Calendar.getInstance();
        calendarRent.set(2024, Calendar.AUGUST, 15);
        rentDate = calendarRent.getTime();

        Calendar calendarReturn = Calendar.getInstance();
        calendarReturn.set(2024, Calendar.SEPTEMBER, 15);
        returnDate = calendarReturn.getTime();

        readerNames = "Jon Doe";
        reader = new Reader();
        reader.setFirstName("Joe");
        reader.setLastName("Doe");
    }

    @Test
    void shouldCheckIfDtoIsNull() {
        ReaderCardDto readerCardDto = readerCardMapper.mapEntityToDto(null);

        Assertions.assertNull(readerCardDto);
    }

    @Test
    void shouldCheckIfEntityIsNull() {
        ReaderCard readerCard = readerCardMapper.mapDtoToEntity(null);

        Assertions.assertNull(readerCard);
    }

    @Test
    void shouldMapEntityToDto() {
        ReaderCard readerCard = new ReaderCard();

        readerCard.setId(1);
        readerCard.setRentDate(rentDate);
        readerCard.setReturnDate(returnDate);
        readerCard.setReader(reader);

        ReaderCardDto readerCardDto = readerCardMapper.mapEntityToDto(readerCard);
        Assertions.assertNotNull(readerCardDto);
        Assertions.assertEquals(readerCard.getId(), readerCardDto.id());
        Assertions.assertEquals(readerCard.getRentDate(), readerCardDto.rentDate());
        Assertions.assertEquals(readerCard.getReturnDate(), readerCardDto.returnDate());
        Assertions.assertEquals(readerCard.getReader().getFirstName() + " " + readerCard.getReader().getLastName(), readerCardDto.readerNames());
    }

    @Test
    void shouldMapDtoToEntity() {
        ReaderCardDto readerCardDto = new ReaderCardDto(1, rentDate, returnDate, readerNames);

        ReaderCard readerCard = readerCardMapper.mapDtoToEntity(readerCardDto);

        Assertions.assertNotNull(readerCard);
        Assertions.assertEquals(readerCardDto.id(), readerCard.getId());
        Assertions.assertEquals(readerCardDto.rentDate(), readerCard.getRentDate());
        Assertions.assertEquals(readerCardDto.returnDate(), readerCard.getReturnDate());
    }
}