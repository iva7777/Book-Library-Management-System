package com.example.booklibrary.library.controller;

import com.example.booklibrary.library.common.response.ApiResponse;
import com.example.booklibrary.library.common.util.ResponseHelper;
import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.model.ReaderCard;
import com.example.booklibrary.library.security.AuthenticationService;
import com.example.booklibrary.library.service.ReaderServiceImpl;
import com.example.booklibrary.library.service.interfaces.ReaderCardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reader-cards")
@CrossOrigin(origins = "http://localhost:4200")
public class ReaderCardController {
    private final ReaderCardService readerCardService;
    private final AuthenticationService authenticationService;
    private final ReaderServiceImpl readerService;

    public ReaderCardController(ReaderCardService readerCardService, AuthenticationService authenticationService, ReaderServiceImpl readerService) {
        this.readerCardService = readerCardService;
        this.authenticationService = authenticationService;
        this.readerService = readerService;
    }

    @GetMapping
    public ResponseEntity<List<ReaderCardDto>> getAllReaderCards() {
        List<ReaderCardDto> readerCards = readerCardService.getAllReaderCards();
        return new ResponseEntity<>(readerCards, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ReaderCardDto>> getReaderCardById(@PathVariable int id) {
        Optional<ReaderCardDto> readerCardOptional = readerCardService.getReaderCardById(id);

        return readerCardOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Reader card with ID " + id + " not found"));
    }

    @GetMapping("/searchByReaderId/{readerId}")
    public ResponseEntity<ApiResponse<ReaderCardDto>> searchReaderCardByReaderId(@Valid @PathVariable int readerId) {
        Optional<ReaderCardDto> readerCardOptional = readerCardService.getReaderCardByReaderId(readerId);

        return readerCardOptional.map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Reader card with userId " + readerId + " not found"));
    }

    @PostMapping
    public ResponseEntity<ReaderCard> saveReaderCard(@Valid @RequestBody ReaderCardDto readerCardDto) {
        ReaderCard readerCard = readerCardService.saveReaderCard(readerCardDto);
        return new ResponseEntity<>(readerCard, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<ReaderCardDto>> updateReaderCard(@PathVariable int id, @Valid @RequestBody ReaderCardDto readerCardDto) {
        Optional<ReaderCardDto> readerCardOptional = readerCardService.updateReaderCard(id, readerCardDto);

        return readerCardOptional
                .map(ResponseHelper::successResponse)
                .orElse(ResponseHelper.notFoundResponse("Reader card with ID " + id + " not found."));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReaderCard(@PathVariable int id) {
        readerCardService.deleteReaderCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
