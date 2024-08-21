package com.example.booklibrary.library.common.util;

import com.example.booklibrary.library.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static <T> ResponseEntity<ApiResponse<T>> generateResponse(boolean success, HttpStatus status, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(success, message, data);
        return new ResponseEntity<>(response, status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> successResponse(T data) {
        return generateResponse(true, HttpStatus.OK, "Operation successful", data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> failureResponse(String message) {
        return generateResponse(false, HttpStatus.BAD_REQUEST, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFoundResponse(String message) {
        return generateResponse(false, HttpStatus.NOT_FOUND, message, null);
    }
}
