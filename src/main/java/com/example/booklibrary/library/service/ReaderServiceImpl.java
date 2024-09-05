package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.ReaderDto;
import com.example.booklibrary.library.mapper.ReaderMapper;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.repository.AppUserRepository;
import com.example.booklibrary.library.repository.ReaderRepository;
import com.example.booklibrary.library.security.AuthenticationService;
import com.example.booklibrary.library.service.interfaces.ReaderService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;
    private final AppUserRepository appUserRepository;
    private final AuthenticationService authenticationService;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderServiceImpl(@NotNull ReaderRepository readerRepository, @NotNull AppUserRepository appUserRepository, @NotNull AuthenticationService authenticationService, @NotNull ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.appUserRepository = appUserRepository;
        this.authenticationService = authenticationService;
        this.readerMapper = readerMapper;
    }

    public List<ReaderDto> getAllReaders() {

        return readerRepository
                .findAll()
                .stream()
                .map(readerMapper::mapEntityToDto)
                .toList();
    }

    public Optional<ReaderDto> getReaderById(int id) {
        return readerRepository.findById(id)
                .map(readerMapper::mapEntityToDto);
    }

    @Override
    public Optional<ReaderDto> getReaderByUserId(int userId) {
        return readerRepository.findReaderByAppUserId(userId)
                .map(readerMapper::mapEntityToDto);
    }

    public Optional<ReaderDto> getOwnReader() {
        String loggedUsername = authenticationService.getAuthenticatedUsername();
        Optional<AppUser> loggedUser = appUserRepository.findByUsername(loggedUsername);

        if (loggedUser.isPresent()) {
            return getReaderByUserId(loggedUser.get().getId());
        }

        return Optional.empty();
    }

    public Reader saveReader(ReaderDto readerDto) {
        Reader reader = readerMapper.mapDtoToEntity(readerDto);

        return readerRepository.save(reader);
    }

    public Optional<ReaderDto> updateReaderInfo(int readerId, ReaderDto readerDetailsDto) {
        return readerRepository.findById(readerId).map(existingReader -> {
            existingReader.setFirstName(readerDetailsDto.firstName());
            existingReader.setLastName(readerDetailsDto.lastName());
            existingReader.setPhone(readerDetailsDto.phone());
            existingReader.setAddress(readerDetailsDto.address());
            existingReader.setEmail(readerDetailsDto.email());
            Reader updatedReader = readerRepository.save(existingReader);
            return readerMapper.mapEntityToDto(updatedReader);
        });
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

    public Optional<ReaderDto> searchReaderByPhoneNumber(String phone) {
        Optional<Reader> optionalReader = readerRepository.findReaderByPhone(phone);

        return optionalReader.map(readerMapper::mapEntityToDto);
    }

    public Optional<ReaderDto> searchReaderByEmail(String email) {
        Optional<Reader> optionalReader = readerRepository.findReaderByEmail(email);

        return optionalReader.map(readerMapper::mapEntityToDto);
    }
}
