package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.model.ReaderCard;

import java.util.List;
import java.util.NoSuchElementException;

public interface ReaderCardService {
    List<ReaderCardDto> getAllReaderCards();

    ReaderCardDto getReaderCardById(int id) throws NoSuchElementException;

    ReaderCard saveReaderCard(ReaderCardDto readerCardDto);

    void updateReaderCard(int readerCardId, ReaderCardDto readerCardDetailsDto) throws NoSuchElementException;

    void deleteReaderCard(int id);
}
