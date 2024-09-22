package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.model.ReaderCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@ActiveProfiles("application-test.properties")
@Transactional
class ReaderMapperTest {
    private final ReaderMapper readerMapper = new ReaderMapper();

    @Test
    void shouldCheckIfDtoIsNull() {
        ReaderDto readerDto = readerMapper.mapEntityToDto(null);

        Assertions.assertNull(readerDto);
    }

    @Test
    void shouldCheckIfEntityIsNull() {
        Reader reader = readerMapper.mapDtoToEntity(null);

        Assertions.assertNull(reader);
    }

    @Test
    void shouldMapEntityToDto() {
        Reader reader = new Reader();
        ReaderCard readerCard = new ReaderCard();
        readerCard.setReader(reader);

        reader.setId(1);
        reader.setFirstName("test");
        reader.setLastName("test");
        reader.setEmail("test");
        reader.setPhone("089654710");
        reader.setAddress("test");
        reader.setReaderCard(readerCard);

        ReaderDto readerDto = readerMapper.mapEntityToDto(reader);
        Assertions.assertNotNull(readerDto);
        Assertions.assertEquals(reader.getId(), readerDto.id());
        Assertions.assertEquals(reader.getFirstName(), readerDto.firstName());
        Assertions.assertEquals(reader.getLastName(), readerDto.lastName());
        Assertions.assertEquals(reader.getEmail(), readerDto.email());
        Assertions.assertEquals(reader.getPhone(), readerDto.phone());
        Assertions.assertEquals(reader.getAddress(), readerDto.address());
    }

    @Test
    void shouldMapDtoToEntity() {
        ReaderCardDto readerCardDto = new ReaderCardDto(1, new Date(), new Date(), "Unknown");
        ReaderDto readerDto = new ReaderDto(1, "test", "test", "test", "test", "test", readerCardDto);

        Reader reader = readerMapper.mapDtoToEntity(readerDto);

        Assertions.assertNotNull(reader);
        Assertions.assertEquals(readerDto.id(), reader.getId());
        Assertions.assertEquals(readerDto.firstName(), reader.getFirstName());
        Assertions.assertEquals(readerDto.lastName(), reader.getLastName());
        Assertions.assertEquals(readerDto.phone(), reader.getPhone());
        Assertions.assertEquals(readerDto.address(), reader.getAddress());
        Assertions.assertEquals(readerDto.email(), reader.getEmail());
    }
}