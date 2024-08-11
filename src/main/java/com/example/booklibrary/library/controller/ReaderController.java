package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.service.interfaces.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            ReaderDto reader = readerService.getReaderById(id);
            return new ResponseEntity<>(reader, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<List<ReaderDto>> searchReadersByName(@PathVariable String name) {
        List<ReaderDto> readers = readerService.searchReaderByName(name);
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @GetMapping("/searchByPhoneNumber/{phoneNumber}")
    public ResponseEntity<ReaderDto> searchReaderByPhoneNumber(@PathVariable String phoneNumber) {
        ReaderDto reader = readerService.searchReaderByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    @GetMapping("/searchByEmail/{email}")
    public ResponseEntity<ReaderDto> searchReaderByEmail(@PathVariable String email) {
        ReaderDto reader = readerService.searchReaderByEmail(email);
        return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reader> saveReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerService.saveReader(readerDto);
        return new ResponseEntity<>(reader, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateReader(@PathVariable int id, @RequestBody ReaderDto readerDto) {
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
