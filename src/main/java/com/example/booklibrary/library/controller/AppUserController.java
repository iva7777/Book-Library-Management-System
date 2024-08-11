package com.example.booklibrary.library.controller;


import com.example.booklibrary.library.dto.AppUserDto;
import com.example.booklibrary.library.model.AppUser;
import com.example.booklibrary.library.model.Role;
import com.example.booklibrary.library.service.interfaces.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<AppUserDto> getAuthorById(@PathVariable int id) {
        try {
            AppUserDto user = appUserService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchByUsername/{username}")
    public ResponseEntity<AppUserDto> searchUsersByUsername(@PathVariable String username) {
        AppUserDto user = appUserService.searchUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/searchByRole/{role}")
    public ResponseEntity<List<AppUserDto>> searchUsersByRole(@PathVariable Role role) {
        List<AppUserDto> users = appUserService.searchUsersByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AppUser> saveAuthor(@RequestBody AppUserDto appUserDto) {
        AppUser appUser = appUserService.saveUser(appUserDto);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody AppUserDto appUserDto) {
        try {
            appUserService.updateUserInfo(id, appUserDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        appUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
