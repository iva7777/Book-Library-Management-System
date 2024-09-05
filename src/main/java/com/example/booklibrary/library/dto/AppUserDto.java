package com.example.booklibrary.library.dto;

import com.example.booklibrary.library.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AppUserDto(int id,
                         @Size(max = 20, message = "Username must be at most 20 characters")
                         @NotBlank
                         String username,
                         @Size(max = 255, message = "Password must be at most 20 characters")
                         @NotBlank
                         String password,
                         @NotNull(message = "Role cannot be null.")
                         Role role) {
}
