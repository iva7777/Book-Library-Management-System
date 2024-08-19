package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.model.ReaderCard;

import java.util.List;
import java.util.Optional;

public interface ReaderCardService {
    List<ReaderCardDto> getAllReaderCards();

    Optional<ReaderCardDto> getReaderCardById(int id);

    ReaderCard saveReaderCard(ReaderCardDto readerCardDto);

    Optional<ReaderCardDto> updateReaderCard(int readerCardId, ReaderCardDto readerCardDetailsDto);

    void deleteReaderCard(int id);
}
