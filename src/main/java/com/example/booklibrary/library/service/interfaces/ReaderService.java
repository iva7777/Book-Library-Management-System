package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.model.Reader;

import java.util.List;
import java.util.NoSuchElementException;

public interface ReaderService {
    List<ReaderDto> getAllReaders();

    ReaderDto getReaderById(int id) throws NoSuchElementException;

    Reader saveReader(ReaderDto readerDto);

    void updateReaderInfo(int readerId, ReaderDto readerDetailsDto) throws NoSuchElementException;

    void deleteReader(int id);

    List<ReaderDto> searchReaderByName(String name);

    ReaderDto searchReaderByPhoneNumber(String phone);

    ReaderDto searchReaderByEmail(String email);
}
