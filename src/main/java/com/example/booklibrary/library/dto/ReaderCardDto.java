package com.example.booklibrary.library.dto;

import java.util.Date;

public record ReaderCardDto(int id,
                            Date rentDate,
                            Date returnDate) {
}
