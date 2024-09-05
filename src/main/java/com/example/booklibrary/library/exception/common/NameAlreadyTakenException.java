package com.example.booklibrary.library.exception.common;

public class NameAlreadyTakenException extends RuntimeException {
    public NameAlreadyTakenException(String message) {
        super(message);
    }

    public NameAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
