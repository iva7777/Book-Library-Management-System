package com.example.booklibrary.library.common.response;

public class ApiResponse<T> {
    private boolean isSuccessful;
    private String message;
    private T data;

    public ApiResponse(boolean isSuccessful, String message, T data) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
