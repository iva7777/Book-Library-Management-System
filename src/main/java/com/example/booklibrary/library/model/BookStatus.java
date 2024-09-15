package com.example.booklibrary.library.model;

import com.example.booklibrary.library.common.BookStatusDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = BookStatusDeserializer.class)
public enum BookStatus {
    available,
    borrowed,
    discarded
}
