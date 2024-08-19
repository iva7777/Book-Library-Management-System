package com.example.booklibrary.library.service;

import com.example.booklibrary.library.dto.AppUserDto;
import com.example.booklibrary.library.mapper.AppUserMapper;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;
import com.example.booklibrary.library.repository.AppUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {
    @Mock
    AppUserRepository appUserRepository;

    AppUserMapper appUserMapper;

    @InjectMocks
    AppUserServiceImpl appUserService;

    private AppUser appUser;
    private AppUserDto appUserDto;

    @BeforeEach
    void setUp() {
        appUserMapper = new AppUserMapper();
        appUserService = new AppUserServiceImpl(appUserRepository, appUserMapper);

        appUser = new AppUser();
        appUser.setId(1);
        appUser.setUsername("user100");
        appUser.setPassword("Qwer123");
        appUser.setRole(Role.librarian);

        appUserDto = new AppUserDto(1, "user100", "Qwer123", Role.librarian);
    }

    @Test
    void shouldGetAllUsers() {
        when(appUserRepository.findAll()).thenReturn(new ArrayList<>(List.of(appUser)));

        String username = "user100";
        Role role = Role.librarian;

        List<AppUserDto> appUsers = appUserService.getAllUsers();

        Assertions.assertEquals(1, appUsers.size());
        Assertions.assertEquals(username, appUsers.getFirst().username());
        Assertions.assertEquals(role, appUsers.getFirst().role());
    }

    @Test
    void shouldGetUserById() {
        when(appUserRepository.findById(1)).thenReturn(Optional.of(appUser));

        Optional<AppUserDto> newDtoOptional = appUserService.getUserById(1);

        Assertions.assertTrue(newDtoOptional.isPresent());
        AppUserDto newDto = newDtoOptional.get();
        Assertions.assertEquals(appUserDto, newDto);
        verify(appUserRepository).findById(1);
    }

    @Test
    void shouldNotGetUserById() {
        when(appUserRepository.findById(1)).thenReturn(Optional.empty());

        Optional<AppUserDto> newDtoOptional = appUserService.getUserById(1);

        Assertions.assertTrue(newDtoOptional.isEmpty());
        verify(appUserRepository).findById(1);
    }

    @Test
    void shouldUpdateUserInfo() {
        when(appUserRepository.findById(1)).thenReturn(Optional.of(appUser));
        when(appUserRepository.save(appUser)).thenReturn(appUser);

        Optional<AppUserDto> updatedUserOptional = appUserService.updateUserInfo(1, appUserDto);

        Assertions.assertTrue(updatedUserOptional.isPresent());
        AppUserDto updatedUser = updatedUserOptional.get();
        Assertions.assertEquals(appUserDto, updatedUser);
        verify(appUserRepository).findById(1);
        verify(appUserRepository).save(appUser);
    }

    @Test
    void shouldNotUpdateUserInfo() {
        when(appUserRepository.findById(1)).thenReturn(Optional.empty());

        Optional<AppUserDto> updatedUserOptional = appUserService.updateUserInfo(1, appUserDto);

        Assertions.assertTrue(updatedUserOptional.isEmpty());
        verify(appUserRepository).findById(1);
    }

    @Test
    void shouldDeleteUser() {
        appUserService.deleteUser(1);

        verify(appUserRepository).deleteById(1);
    }

    @Test
    void shouldSearchUserByUsername() {
        String username = "johndoe";
        when(appUserRepository.findByUsername(username)).thenReturn(Optional.of(appUser));

        Optional<AppUserDto> newDtoOptional = appUserService.searchUserByUsername(username);

        Assertions.assertTrue(newDtoOptional.isPresent());
        AppUserDto newDto = newDtoOptional.get();
        Assertions.assertEquals(appUserDto, newDto);
        verify(appUserRepository).findByUsername(username);
    }

    @Test
    void shouldSearchUserByUsernameAndReturnOptional() {
        String username = "nonexistentuser";
        when(appUserRepository.findByUsername(username)).thenReturn(Optional.empty());


        Optional<AppUserDto> newDtoOptional = appUserService.searchUserByUsername(username);

        Assertions.assertTrue(newDtoOptional.isEmpty());
        verify(appUserRepository).findByUsername(username);
    }

    @Test
    void shouldSearchUsersByRole() {
        Role role = Role.librarian;

        when(appUserRepository.findByRole(role)).thenReturn(new ArrayList<>(List.of(appUser)));

        List<AppUserDto> appUsers = appUserService.searchUsersByRole(role);

        Assertions.assertEquals(1, appUsers.size());
        Assertions.assertEquals(role, appUsers.getFirst().role());
    }

    @Test
    void shouldSearchUsersByRoleAndReturnEmptyList() {
        when(appUserRepository.findByRole(any())).thenReturn(new ArrayList<>());

        List<AppUserDto> appUsers = appUserService.searchUsersByRole(Role.librarian);

        Assertions.assertEquals(0, appUsers.size());
    }
}