package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.common.response.ApiResponse;
import com.example.booklibrary.library.common.util.ResponseHelper;
import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.service.interfaces.ReaderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<ApiResponse<ReaderDto>> getReaderById(@PathVariable int id) {
        Optional<ReaderDto> readerOptional = readerService.getReaderById(id);

        return readerOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Reader with ID " + id + " not found"));
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<?> searchReadersByName(@Valid @PathVariable String name) {
        List<ReaderDto> readers = readerService.searchReaderByName(name);
        if (readers.isEmpty()) {
            return ResponseHelper.notFoundResponse("No readers found for the given name.");
        }

        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @GetMapping("/searchByPhoneNumber/{phoneNumber}")
    public ResponseEntity<ApiResponse<ReaderDto>> searchReaderByPhoneNumber(@Valid @PathVariable String phoneNumber) {
        Optional<ReaderDto> readerOptional = readerService.searchReaderByPhoneNumber(phoneNumber);

        return readerOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Reader with phone number " + phoneNumber + " not found"));
    }

    @GetMapping("/searchByEmail/{email}")
    public ResponseEntity<ApiResponse<ReaderDto>> searchReaderByEmail(@Valid @PathVariable String email) {
        Optional<ReaderDto> readerOptional = readerService.searchReaderByEmail(email);

        return readerOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Reader with email " + email + " not found"));
    }

    @GetMapping("/searchByUserId/{userId}")
    public ResponseEntity<ApiResponse<ReaderDto>> searchReaderByUserId(@Valid @PathVariable int userId) {
        Optional<ReaderDto> readerOptional = readerService.getReaderByUserId(userId);

        return readerOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Reader with userId " + userId + " not found"));
    }

    @GetMapping("/loggedReader")
    public ResponseEntity<ApiResponse<ReaderDto>> getLoggedReader() {
        Optional<ReaderDto> readerOptional = readerService.getOwnReader();

        return readerOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Reader not logged in"));
    }

    @PostMapping
    public ResponseEntity<Reader> saveReader(@Valid @RequestBody ReaderDto readerDto) {
        Reader reader = readerService.saveReader(readerDto);
        return new ResponseEntity<>(reader, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<ReaderDto>> updateReader(@PathVariable int id, @Valid @RequestBody ReaderDto readerDto) {
        Optional<ReaderDto> readerOptional = readerService.updateReaderInfo(id, readerDto);

        return readerOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Reader with ID " + id + " not found"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable int id) {
        readerService.deleteReader(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
