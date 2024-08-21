package com.example.booklibrary.library.exception.common;

import java.time.LocalDate;

public class BookNotAvailableException extends RuntimeException {
    private LocalDate availableDate;
    public BookNotAvailableException(String message) {
        super(message);
        this.availableDate = null;
    }

    public BookNotAvailableException(String message, Throwable cause) {
        super(message, cause);
        this.availableDate = null;
    }

    public BookNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.availableDate = null;
    }

    public BookNotAvailableException(String message, LocalDate availableDate) {
        super(String.format("%s Available on: %s", message, availableDate));
        this.availableDate = availableDate;
    }

    public BookNotAvailableException(String message, LocalDate availableDate, Throwable cause) {
        super(String.format("%s Available on: %s", message, availableDate), cause);
        this.availableDate = availableDate;
    }

    public BookNotAvailableException(String message, LocalDate availableDate, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(String.format("%s Available on: %s", message, availableDate), cause, enableSuppression, writableStackTrace);
        this.availableDate = availableDate;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }
}
