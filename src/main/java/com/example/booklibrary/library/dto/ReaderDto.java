package com.example.booklibrary.library.dto;

public record ReaderDto(int id,
                        String firstName,
                        String lastName,
                        String phone,
                        String address,
                        String email,
                        ReaderCardDto readerCard) {
}
