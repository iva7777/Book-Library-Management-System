package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.service.interfaces.ReaderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/readers")
public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getAllReaders() {
        List<ReaderDto> readers = readerService.getAllReaders();
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReaderDto> getReaderById(@PathVariable int id) {
        Optional<ReaderDto> readerOptional = readerService.getReaderById(id);

        return readerOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Reader with ID " + id + " not found"));
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<List<ReaderDto>> searchReadersByName(@Valid @PathVariable String name) {
        List<ReaderDto> readers = readerService.searchReaderByName(name);
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @GetMapping("/searchByPhoneNumber/{phoneNumber}")
    public ResponseEntity<ReaderDto> searchReaderByPhoneNumber(@Valid @PathVariable String phoneNumber) {
        Optional<ReaderDto> readerOptional = readerService.searchReaderByPhoneNumber(phoneNumber);

        return readerOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Reader with phone number " + phoneNumber + " not found"));
    }

    @GetMapping("/searchByEmail/{email}")
    public ResponseEntity<ReaderDto> searchReaderByEmail(@Valid @PathVariable String email) {
        Optional<ReaderDto> readerOptional = readerService.searchReaderByEmail(email);

        return readerOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("Reader with email " + email + " not found"));
    }

    @PostMapping
    public ResponseEntity<Reader> saveReader(@Valid @RequestBody ReaderDto readerDto) {
        Reader reader = readerService.saveReader(readerDto);
        return new ResponseEntity<>(reader, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateReader(@PathVariable int id, @Valid @RequestBody ReaderDto readerDto) {
        try {
            readerService.updateReaderInfo(id, readerDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable int id) {
        readerService.deleteReader(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
