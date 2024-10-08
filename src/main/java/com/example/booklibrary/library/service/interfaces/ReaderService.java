package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.search.ReaderSearchRequest;

import java.util.List;
import java.util.Optional;

public interface ReaderService {
    List<ReaderDto> getAllReaders();

    Optional<ReaderDto> getReaderById(int id);

    Optional<ReaderDto> getReaderByUserId(int userId);

    Optional<ReaderDto> getOwnReader();

    Reader saveReader(ReaderDto readerDto);

    Optional<ReaderDto> updateReaderInfo(int readerId, ReaderDto readerDetailsDto);

    void deleteReader(int id);

    List<ReaderDto> searchReaderByName(String name);

    Optional<ReaderDto> searchReaderByPhoneNumber(String phone);

    Optional<ReaderDto> searchReaderByEmail(String email);

    List<ReaderDto> findReadersByCriteria(ReaderSearchRequest request);
}
