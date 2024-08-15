package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.mapper.ReaderMapper;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.repository.ReaderRepository;
import com.example.booklibrary.library.service.interfaces.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderServiceImpl(ReaderRepository readerRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    public List<ReaderDto> getAllReaders() {

        return readerRepository
                .findAll()
                .stream()
                .map(readerMapper::mapEntityToDto)
                .toList();
    }

    public ReaderDto getReaderById(int id) {
        Reader reader = readerRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reader with ID " + id + " not found."));

        return readerMapper.mapEntityToDto(reader);
    }

    public Reader saveReader(ReaderDto readerDto) {
        Reader reader = readerMapper.mapDtoToEntity(readerDto);

        return readerRepository.save(reader);
    }

    public void updateReaderInfo(int readerId, ReaderDto readerDetailsDto) {
//        Reader reader = readerRepository
//                .findById(readerId)
//                .orElseThrow(() -> new NoSuchElementException("Reader with ID " + readerId + " not found."));
//
//        Reader readerDetails = readerMapper.mapDtoToEntity(readerDetailsDto);
//
//        reader.setFirstName(readerDetails.getFirstName());
//        reader.setLastName(readerDetails.getLastName());
//        reader.setPhone(readerDetails.getPhone());
//        reader.setAddress(readerDetails.getAddress());
//        reader.setEmail(readerDetails.getEmail());
//
//        readerRepository.save(reader);

        Optional<Reader> existingReaderOptional = readerRepository.findById(readerId);
        if (existingReaderOptional.isEmpty()) {
            throw new NoSuchElementException("Reader with ID " + readerId + "not found.");
        }
        Reader existingReader = existingReaderOptional.get();


        existingReader.setFirstName(readerDetailsDto.firstName());
        existingReader.setLastName(readerDetailsDto.lastName());
        existingReader.setPhone(readerDetailsDto.phone());
        existingReader.setAddress(readerDetailsDto.address());
        existingReader.setEmail(readerDetailsDto.email());

        Reader updatedReader = readerRepository.save(existingReader);

        readerMapper.mapEntityToDto(updatedReader);
    }

    public void deleteReader(int id){
        readerRepository.deleteById(id);
    }

    public List<ReaderDto> searchReaderByName(String name) {
        return readerRepository.findReaderByNameContaining(name)
                .stream()
                .map(readerMapper::mapEntityToDto)
                .toList();
    }

    public ReaderDto searchReaderByPhoneNumber(String phone) {
        Reader reader = readerRepository
                .findReaderByPhone(phone)
                .orElseThrow(() -> new NoSuchElementException("Reader with phone " + phone + " not found."));

        return readerMapper.mapEntityToDto(reader);
    }

    public ReaderDto searchReaderByEmail(String email) {
        Reader reader = readerRepository
                .findReaderByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Reader with email " + email + " not found."));

        return readerMapper.mapEntityToDto(reader);
    }
}
