package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.AppUserDto;
import com.example.booklibrary.library.mapper.AppUserMapper;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;
import com.example.booklibrary.library.repository.AppUserRepository;
import com.example.booklibrary.library.service.interfaces.AppUserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Autowired
    public AppUserServiceImpl(@NotNull AppUserRepository appUserRepository, @NotNull AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    @Override
    public List<AppUserDto> getAllUsers() {
        return appUserRepository
                .findAll()
                .stream()
                .map(appUserMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public Optional<AppUserDto> getUserById(int id) {
        return appUserRepository.findById(id)
                .map(appUserMapper::mapEntityToDto);
    }

    @Override
    public AppUser saveUser(AppUserDto appUserDto) {
        AppUser appUser = appUserMapper.mapDtoToEntity(appUserDto);

        return appUserRepository.save(appUser);
    }

    @Override
    public Optional<AppUserDto> updateUserInfo(int userId, AppUserDto userDetailsDto) {
        return appUserRepository.findById(userId).map(existingUser -> {
            existingUser.setUsername(userDetailsDto.username());
            existingUser.setPassword(userDetailsDto.password());
            existingUser.setRole(userDetailsDto.role());
            AppUser updatedUser = appUserRepository.save(existingUser);
            return appUserMapper.mapEntityToDto(updatedUser);
        });
    }

    @Override
    public void deleteUser(int id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public Optional<AppUserDto> searchUserByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .map(appUserMapper::mapEntityToDto);
    }

    @Override
    public List<AppUserDto> searchUsersByRole(Role role) {
        return appUserRepository
                .findByRole(role).stream()
                .map(appUserMapper::mapEntityToDto)
                .toList();
    }
}
