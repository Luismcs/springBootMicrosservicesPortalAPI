package com.exercise.exercise.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public CustomException(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
