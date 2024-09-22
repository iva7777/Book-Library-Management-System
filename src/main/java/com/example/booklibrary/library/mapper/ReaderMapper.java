package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.model.ReaderCard;
import org.mapstruct.Mapper;

import java.util.Date;

@Mapper(componentModel = "spring")
public class ReaderMapper {
    public ReaderDto mapEntityToDto(Reader reader){
        if (reader == null){
            return null;
        }

        Integer id = null;
        String firstName = null;
        String lastName = null;
        String phone = null;
        String address = null;
        String email = null;
        ReaderCardDto readerCard = null;

        id = reader.getId();
        firstName = reader.getFirstName();
        lastName = reader.getLastName();
        phone = reader.getPhone();
        address = reader.getAddress();
        email = reader.getEmail();
        readerCard = mapReaderCardToReaderCardDto(reader.getReaderCard());

        return new ReaderDto(id, firstName, lastName, phone, address, email, readerCard);
    }

    public Reader mapDtoToEntity(ReaderDto readerDto){
        if (readerDto == null){
            return null;
        }

        Reader reader = new Reader();

        reader.setId(readerDto.id());
        reader.setFirstName(readerDto.firstName());
        reader.setLastName(readerDto.lastName());
        reader.setPhone(readerDto.phone());
        reader.setAddress(readerDto.address());
        reader.setEmail(readerDto.email());
        reader.setReaderCard(mapReaderCardDtoToReaderCard(readerDto.readerCard()));

        return reader;
    }

    protected ReaderCardDto mapReaderCardToReaderCardDto(ReaderCard readerCard){
        if (readerCard == null){
            return null;
        }

        Integer id = null;
        Date rentDate = null;
        Date returnDate = null;
        String readerNames = null;

        id = readerCard.getId();
        rentDate = readerCard.getRentDate();
        returnDate = readerCard.getReturnDate();
        readerNames = readerCard.getReader().getFirstName() + " " + readerCard.getReader().getLastName();

        return new ReaderCardDto(id, rentDate, returnDate, readerNames);
    }

    protected ReaderCard mapReaderCardDtoToReaderCard(ReaderCardDto readerCardDto){
        if (readerCardDto == null){
            return null;
        }

        ReaderCard readerCard = new ReaderCard();

        readerCard.setId(readerCardDto.id());
        readerCard.setRentDate(readerCardDto.rentDate());
        readerCard.setReturnDate(readerCardDto.returnDate());

        return readerCard;
    }
}
