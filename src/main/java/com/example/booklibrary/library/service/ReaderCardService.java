package com.example.booklibrary.library.service;

import com.example.booklibrary.library.model.ReaderCard;
import com.example.booklibrary.library.repository.ReaderCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReaderCardService {
    private final ReaderCardRepository readerCardRepository;

    @Autowired
    public ReaderCardService(ReaderCardRepository readerCardRepository) {
        this.readerCardRepository = readerCardRepository;
    }

    public List<ReaderCard> getAllReaderCards(){
        return readerCardRepository.findAll();
    }

    public ReaderCard getReaderCardById(int id){
        return readerCardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Reader card not found."));
    }

    public ReaderCard saveReaderCard(ReaderCard readerCard){
        return readerCardRepository.save(readerCard);
    }

    public void updateReaderCard(int readerCardId, ReaderCard readerCardDetails){
        Optional<ReaderCard> optionalReaderCard = readerCardRepository.findById(readerCardId);
        if (optionalReaderCard.isPresent()){
            ReaderCard readerCard = optionalReaderCard.get();
            readerCard.setReader(readerCardDetails.getReader());
            readerCard.setBook(readerCardDetails.getBook());
            readerCard.setRentDate(readerCardDetails.getRentDate());
            readerCard.setReturnDate(readerCardDetails.getReturnDate());
            readerCardRepository.save(readerCard);
        } else {
            throw new RuntimeException("Reader card with ID " + readerCardId + " not found.");
        }
    }

    public void deleteReaderCard(int id){
        readerCardRepository.deleteById(id);
    }
}
