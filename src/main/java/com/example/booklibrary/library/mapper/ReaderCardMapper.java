package com.example.booklibrary.library.mapper;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.model.ReaderCard;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public class ReaderCardMapper {
    public ReaderCardDto mapEntityToDto(ReaderCard readerCard){
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
        readerNames = readerCard.getReader() == null
                ? "Unknown"
                : readerCard.getReader().getFirstName() + " " + readerCard.getReader().getLastName();

        return new ReaderCardDto(id, rentDate, returnDate, readerNames);
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

    private List<Reader> fetchReadersByNames(String readerNames) {
        if (readerNames == null || readerNames.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String[] names = readerNames.split(",\\s*");
        List<Reader> readers = new ArrayList<>();

        for (String name : names) {
            String[] splitName = name.trim().split("\\s+");
            if (splitName.length >= 2) {
                String firstName = splitName[0];
                String lastName = splitName[1];

                Reader reader = new Reader();
                reader.setFirstName(firstName);
                reader.setLastName(lastName);
                readers.add(reader);
            }
        }
        return readers;
    }

}
