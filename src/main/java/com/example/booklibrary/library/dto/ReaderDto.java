package com.example.booklibrary.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReaderDto(int id,
                        @Size(max = 30, message = "First name must be at most 30 characters")
                        @NotBlank
                        String firstName,
                        String lastName,
                        @Size(max = 15, message = "Phone number must be at most 15 characters")
                        @NotBlank
                        String phone,
                        @Size(max = 80, message = "Address must be at most 80 characters")
                        @NotBlank
                        String address,
                        @Email
                        String email,
                        ReaderCardDto readerCard) {
}
