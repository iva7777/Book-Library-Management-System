package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.AppUserDto;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    List<AppUserDto> getAllUsers();

    Optional<AppUserDto> getUserById(int id);

    AppUser saveUser(AppUserDto appUserDto);

    Optional<AppUserDto> updateUserInfo(int userId, AppUserDto userDetailsDto);

    void deleteUser(int id);

    Optional<AppUserDto> searchUserByUsername(String username);
    List<AppUserDto> searchUsersByRole(Role role);
}
