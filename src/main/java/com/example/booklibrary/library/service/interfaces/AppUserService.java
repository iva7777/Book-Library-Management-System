package com.example.booklibrary.library.service.interfaces;

import com.example.booklibrary.library.dto.AppUserDto;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface AppUserService {
    List<AppUserDto> getAllUsers();

    AppUserDto getUserById(int id) throws NoSuchElementException;

    AppUser saveUser(AppUserDto appUserDto);

    void updateUserInfo(int userId, AppUserDto userDetailsDto) throws NoSuchElementException;

    void deleteUser(int id);
    AppUserDto searchUserByUsername(String username);
    List<AppUserDto> searchUsersByRole(Role role);
}
