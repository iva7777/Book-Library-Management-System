package com.example.booklibrary.library.dto;

import com.example.booklibrary.library.model.BookStatus;

public record BookDto(int id,
                      String title,
                      String publisher,
                      String isbn,
                      String genre,
                      BookStatus status) {
}
