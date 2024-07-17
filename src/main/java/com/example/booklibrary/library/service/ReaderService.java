package com.example.booklibrary.library.service;

import com.example.booklibrary.library.model.Author;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<Reader> getAllReaders(){
        return readerRepository.findAll();
    }

    public Reader getReaderById(int id){
        return readerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Reader not found."));
    }

    public Reader saveReader(Reader reader){
        return readerRepository.save(reader);
    }

    public void updateReaderInfo(int readerId, Reader readerDetails){
        Optional<Reader> optionalReader = readerRepository.findById(readerId);
        if (optionalReader.isPresent()){
            Reader reader = optionalReader.get();
            reader.setFirstName(readerDetails.getFirstName());
            reader.setLastName(readerDetails.getLastName());
            reader.setPhone(readerDetails.getPhone());
            reader.setAddress(readerDetails.getAddress());
            reader.setSchoolClass(readerDetails.getSchoolClass());
        } else {
            throw new RuntimeException("Reader with ID " + readerId + " not found.");
        }
    }

    public void deleteReader(int id){
        readerRepository.deleteById(id);
    }

    public List<Reader> searchReaderByName(String name){
        return readerRepository.findReaderByNameContaining(name);
    }

    public Reader searchReaderByPhoneNumber(String phone){
        return readerRepository.findReaderByPhone(phone);
    }
}
