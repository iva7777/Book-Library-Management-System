package com.example.booklibrary.library.dto;

import com.example.booklibrary.library.model.BookStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookDto(int id,
                      @Size(max = 30, message = "Title must be at most 30 characters")
                      @NotNull
                      String title,
                      @Size(max = 20, message = "Publisher must be at most 20 characters")
                      String publisher,
                      @Size(max = 15, message = "ISBN must be at most 15 characters")
                      String isbn,
                      @Size(max = 20, message = "Genre must be at most 20 characters")
                      String genre,
                      @NotNull(message = "Book status cannot be null.")
                      BookStatus status,
                      @NotNull(message = "Authors cannot be null")
                      String authorNames) {
}
