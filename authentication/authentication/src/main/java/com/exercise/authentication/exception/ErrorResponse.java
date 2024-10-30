package com.exercise.authentication.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private LocalDateTime localDateTime;

    public ErrorResponse(HttpStatus status, String message, LocalDateTime localDateTime) {
        this.status = status;
        this.message = message;
        this.localDateTime = localDateTime;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
