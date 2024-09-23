package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.ReaderCardDto;
import com.example.booklibrary.library.mapper.ReaderCardMapper;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.model.ReaderCard;
import com.example.booklibrary.library.repository.AppUserRepository;
import com.example.booklibrary.library.repository.ReaderCardRepository;
import com.example.booklibrary.library.repository.ReaderRepository;
import com.example.booklibrary.library.security.AuthenticationService;
import com.example.booklibrary.library.service.interfaces.ReaderCardService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderCardServiceImpl implements ReaderCardService {
    private final ReaderCardRepository readerCardRepository;
    private final ReaderRepository readerRepository;
    private final AuthenticationService authenticationService;
    private final AppUserRepository appUserRepository;
    private final ReaderCardMapper readerCardMapper;

    @Autowired
    public ReaderCardServiceImpl(@NotNull ReaderCardRepository readerCardRepository, ReaderRepository readerRepository, AuthenticationService authenticationService, AppUserRepository appUserRepository, @NotNull ReaderCardMapper readerCardMapper) {
        this.readerCardRepository = readerCardRepository;
        this.readerRepository = readerRepository;
        this.authenticationService = authenticationService;
        this.appUserRepository = appUserRepository;
        this.readerCardMapper = readerCardMapper;
    }

    public List<ReaderCardDto> getAllReaderCards() {

        return readerCardRepository
                .findAll()
                .stream()
                .map(readerCardMapper::mapEntityToDto)
                .toList();
    }

    public Optional<ReaderCardDto> getReaderCardById(int id) {
        return readerCardRepository.findById(id)
                .map(readerCardMapper::mapEntityToDto);
    }

    @Override
    public Optional<ReaderCardDto> getReaderCardByReaderId(int readerId) {
        return readerCardRepository.findByReaderId(readerId)
                .map(readerCardMapper::mapEntityToDto);
    }

    @Override
    public Optional<ReaderCardDto> getOwnReaderCard() {
        String loggedUsername = authenticationService.getAuthenticatedUsername();
        Optional<AppUser> loggedUser = appUserRepository.findByUsername(loggedUsername);

        if (loggedUser.isPresent()) {
            AppUser logged = loggedUser.get();
            int loggedUserId = logged.getId();
            Optional<Reader> loggedReader = readerRepository.findReaderByAppUserId(loggedUserId);
            int readerId = loggedReader.get().getId();
            Optional<ReaderCardDto> result = getReaderCardByReaderId(readerId);
            return result;
        }

        return Optional.empty();
    }

    public ReaderCard saveReaderCard(ReaderCardDto readerCardDto) {
        ReaderCard readerCard = readerCardMapper.mapDtoToEntity(readerCardDto);

        ReaderCard savedCard = readerCardRepository.save(readerCard);
        validateReader(savedCard, readerCardDto.readerNames());

        return savedCard;
    }

    public Optional<ReaderCardDto> updateReaderCard(int readerCardId, ReaderCardDto readerCardDetailsDto) {
        Optional<ReaderCard> optionalReaderCard = readerCardRepository.findById(readerCardId);

        if (optionalReaderCard.isEmpty()) {
            return Optional.empty();
        }
        ReaderCard readerCard = optionalReaderCard.get();


        ReaderCard readerCardDetails = readerCardMapper.mapDtoToEntity(readerCardDetailsDto);

        readerCard.setReader(readerCardDetails.getReader());
        readerCard.setBook(readerCardDetails.getBook());
        readerCard.setRentDate(readerCardDetails.getRentDate());
        readerCard.setReturnDate(readerCardDetails.getReturnDate());

        readerCardRepository.save(readerCard);


        ReaderCardDto updatedReaderCardDto = readerCardMapper.mapEntityToDto(readerCard);


        return Optional.of(updatedReaderCardDto);
    }

    public void deleteReaderCard(int id){
        readerCardRepository.deleteById(id);
    }

    private void validateReader(ReaderCard readerCard, String readerName) {
        if (readerName == null || readerName.trim().isEmpty()) {
            return;
        }

        String[] splitName = readerName.trim().split("\\s+");
        if (splitName.length >= 2) {
            String firstName = splitName[0];
            String lastName = splitName[1];

            Optional<Reader> optionalReader = readerRepository.findByFirstNameAndLastName(firstName, lastName);
            Reader reader;

            if (!optionalReader.isPresent()) {
                reader = new Reader();
                reader.setFirstName(firstName);
                reader.setLastName(lastName);
                reader = readerRepository.save(reader);
            } else {
                reader = optionalReader.get();
            }

            readerCard.setReader(reader);
        }
    }

}
