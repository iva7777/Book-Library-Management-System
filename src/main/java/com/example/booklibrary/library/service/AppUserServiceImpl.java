package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.AppUserDto;
import com.example.booklibrary.library.mapper.AppUserMapper;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;
import com.example.booklibrary.library.repository.AppUserRepository;
import com.example.booklibrary.library.service.interfaces.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
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
    public AppUserDto getUserById(int id) {
        AppUser appUser = appUserRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found."));

        return appUserMapper.mapEntityToDto(appUser);
    }

    @Override
    public AppUser saveUser(AppUserDto appUserDto) {
        AppUser appUser = appUserMapper.mapDtoToEntity(appUserDto);

        return appUserRepository.save(appUser);
    }

    @Override
    public void updateUserInfo(int userId, AppUserDto userDetailsDto) {
        AppUser appUser = appUserRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userId + " not found."));

        AppUser appUserDetails = appUserMapper.mapDtoToEntity(userDetailsDto);

        appUser.setUsername(appUserDetails.getUsername());
        appUser.setPassword(appUserDetails.getPassword());
        appUser.setRole(appUserDetails.getRole());

        appUserRepository.save(appUser);
    }

    @Override
    public void deleteUser(int id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public AppUserDto searchUserByUsername(String username) {
        AppUser appUser = appUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User with username " + username + " not found."));

        return appUserMapper.mapEntityToDto(appUser);
    }

    @Override
    public List<AppUserDto> searchUsersByRole(Role role) {
        return appUserRepository
                .findByRole(role).stream()
                .map(appUserMapper::mapEntityToDto)
                .toList();
    }
}
