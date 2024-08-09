package com.example.booklibrary.library.dto;

import com.example.booklibrary.library.model.Reader;
import com.example.booklibrary.library.model.Role;

public record AppUserDto(int id,
                         String username,
                         String password,
                         Role role,
                         Reader reader) {
}
