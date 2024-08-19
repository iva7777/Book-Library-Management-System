package com.example.booklibrary.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorDto(int id,
                        @Size(max = 30, message = "First name must be at most 30 characters")
                        @NotBlank
                        String firstName,
                        @Size(max = 30, message = "Last name must be at most 30 characters")
                        @NotBlank
                        String lastName,
                        String bio) {
}
