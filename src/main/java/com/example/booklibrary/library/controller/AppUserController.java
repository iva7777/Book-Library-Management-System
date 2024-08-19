package com.example.booklibrary.library.controller;


import com.example.booklibrary.library.dto.AppUserDto;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;
import com.example.booklibrary.library.service.interfaces.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public ResponseEntity<List<AppUserDto>> getAllUsers() {
        List<AppUserDto> users = appUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AppUserDto> getUserById(@PathVariable int id) {
        Optional<AppUserDto> userOptional = appUserService.getUserById(id);

        return userOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));
    }

    @GetMapping("/searchByUsername/{username}")
    public ResponseEntity<AppUserDto> searchUsersByUsername(@Valid @PathVariable String username) {
        Optional<AppUserDto> userOptional = appUserService.searchUserByUsername(username);

        return userOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("User with username " + username + " not found"));
    }

    @GetMapping("/searchByRole/{role}")
    public ResponseEntity<List<AppUserDto>> searchUsersByRole(@Valid @PathVariable Role role) {
        List<AppUserDto> users = appUserService.searchUsersByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppUser> saveAuthor(@Valid @RequestBody AppUserDto appUserDto) {
        AppUser appUser = appUserService.saveUser(appUserDto);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppUserDto> updateUser(@PathVariable int id, @Valid @RequestBody AppUserDto appUserDto) {
        Optional<AppUserDto> userOptional = appUserService.updateUserInfo(id, appUserDto);

        return userOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        appUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
