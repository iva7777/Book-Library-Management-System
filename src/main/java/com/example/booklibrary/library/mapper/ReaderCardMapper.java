package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.model.ReaderCard;

import java.util.Date;

public class ReaderCardMapper {
    public ReaderCardDto mapEntityToDto(ReaderCard readerCard){
        if (readerCard == null){
            return null;
        }

        Integer id = null;
        Date rentDate = null;
        Date returnDate = null;

        id = readerCard.getId();
        rentDate = readerCard.getRentDate();
        returnDate = readerCard.getReturnDate();

        return new ReaderCardDto(id, rentDate, returnDate);
    }

    public ReaderCard mapDtoToEntity(ReaderCardDto readerCardDto){
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
