package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.mapper.ReaderCardMapper;
import com.example.booklibrary.library.model.ReaderCard;
import com.example.booklibrary.library.repository.ReaderCardRepository;
import com.example.booklibrary.library.service.interfaces.ReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReaderCardServiceImpl implements ReaderCardService {
    private final ReaderCardRepository readerCardRepository;
    private final ReaderCardMapper readerCardMapper;

    @Autowired
    public ReaderCardServiceImpl(ReaderCardRepository readerCardRepository, ReaderCardMapper readerCardMapper) {
        this.readerCardRepository = readerCardRepository;
        this.readerCardMapper = readerCardMapper;
    }

    public List<ReaderCardDto> getAllReaderCards() {

        return readerCardRepository
                .findAll()
                .stream()
                .map(readerCardMapper::mapEntityToDto)
                .toList();
    }

    public ReaderCardDto getReaderCardById(int id) {
        ReaderCard readerCard = readerCardRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reader card with ID " + id + " not found."));

        return readerCardMapper.mapEntityToDto(readerCard);
    }

    public ReaderCard saveReaderCard(ReaderCardDto readerCardDto) {
        ReaderCard readerCard = readerCardMapper.mapDtoToEntity(readerCardDto);

        return readerCardRepository.save(readerCard);
    }

    public void updateReaderCard(int readerCardId, ReaderCardDto readerCardDetailsDto) {
        ReaderCard readerCard = readerCardRepository
                .findById(readerCardId)
                .orElseThrow(() -> new NoSuchElementException("Reader card with ID " + readerCardId + " not found."));

        ReaderCard readerCardDetails = readerCardMapper.mapDtoToEntity(readerCardDetailsDto);

        readerCard.setReader(readerCardDetails.getReader());
        readerCard.setBook(readerCardDetails.getBook());
        readerCard.setRentDate(readerCardDetails.getRentDate());
        readerCard.setReturnDate(readerCardDetails.getReturnDate());

        readerCardRepository.save(readerCard);
    }

    public void deleteReaderCard(int id){
        readerCardRepository.deleteById(id);
    }
}
