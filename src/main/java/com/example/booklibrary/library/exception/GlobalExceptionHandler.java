package com.example.booklibrary.library.exception;

import com.example.booklibrary.library.common.response.ApiResponse;
import com.example.booklibrary.library.exception.common.BookNotAvailableException;
import com.example.booklibrary.library.exception.common.NameAlreadyTakenException;
import com.example.booklibrary.library.exception.common.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<ApiResponse<Object>> handleBookNotAvailableException(BookNotAvailableException ex) {
        logger.warn(ex.getMessage(), ex);

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                "The book is not available.",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NameAlreadyTakenException.class)
    public ResponseEntity<ApiResponse<Object>> handleNameAlreadyTakenException(NameAlreadyTakenException ex) {
        logger.warn(ex.getMessage(), ex);

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                "This username is already taken!",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(NotFoundException ex) {
        logger.warn(ex.getMessage(), ex);

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                "This user is not found!",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<Object>> handleNullPointerException(NullPointerException ex) {
        logger.error("Null Pointer Exception occurred.", ex);

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                "A null pointer error occurred. Please check your request.",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(NullPointerException ex) {
        logger.error("Illegal Argument Exception occurred.", ex);

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                "An illegal argument error occurred. Please check your request.",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException ex) {
        logger.error("Runtime Exception occurred.", ex);

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                "A runtime error occurred. Please check your request.",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleUnexpectedException(Exception ex) {
        logger.error("An unexpected error occurred.", ex);
        logger.error("Error Message: {}", ex.getMessage());
        logger.error("Stack Trace: {}", ex.getStackTrace());

        ApiResponse<Object> response = new ApiResponse<>(
                false,
                "An unexpected error occurred. Please try again later.",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
