package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.model.ReaderCard;
import com.example.booklibrary.library.service.interfaces.ReaderCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/reader-cards")
public class ReaderCardController {
    private final ReaderCardService readerCardService;

    public ReaderCardController(ReaderCardService readerCardService) {
        this.readerCardService = readerCardService;
    }

    @GetMapping
    public ResponseEntity<List<ReaderCardDto>> getAllReaderCards() {
        List<ReaderCardDto> readerCards = readerCardService.getAllReaderCards();
        return new ResponseEntity<>(readerCards, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReaderCardDto> getReaderCardById(@PathVariable int id) {
        try {
            ReaderCardDto readerCard = readerCardService.getReaderCardById(id);
            return new ResponseEntity<>(readerCard, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ReaderCard> saveReaderCard(@RequestBody ReaderCardDto readerCardDto) {
        ReaderCard readerCard = readerCardService.saveReaderCard(readerCardDto);
        return new ResponseEntity<>(readerCard, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateReaderCard(@PathVariable int id, @RequestBody ReaderCardDto readerCardDto) {
        try {
            readerCardService.updateReaderCard(id, readerCardDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReaderCard(@PathVariable int id) {
        readerCardService.deleteReaderCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
