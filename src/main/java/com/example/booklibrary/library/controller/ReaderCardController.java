package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.model.ReaderCard;
import com.example.booklibrary.library.service.interfaces.ReaderCardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<ReaderCardDto> readerCardOptional = readerCardService.getReaderCardById(id);

        return readerCardOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Reader card with ID " + id + " not found"));
    }

    @PostMapping
    public ResponseEntity<ReaderCard> saveReaderCard(@Valid @RequestBody ReaderCardDto readerCardDto) {
        ReaderCard readerCard = readerCardService.saveReaderCard(readerCardDto);
        return new ResponseEntity<>(readerCard, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReaderCardDto> updateReaderCard(@PathVariable int id, @Valid @RequestBody ReaderCardDto readerCardDto) {
        Optional<ReaderCardDto> readerCardOptional = readerCardService.updateReaderCard(id, readerCardDto);

        return readerCardOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Reader card with ID " + id + " not found"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReaderCard(@PathVariable int id) {
        readerCardService.deleteReaderCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
