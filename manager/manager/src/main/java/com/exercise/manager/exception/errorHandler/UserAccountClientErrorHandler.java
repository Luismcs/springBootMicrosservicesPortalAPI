package com.exercise.manager.exception.errorHandler;

import com.exercise.manager.exception.CustomException;
import com.exercise.manager.exception.ErrorMessage;
import com.exercise.manager.exception.ErrorResponse;
import com.exercise.manager.utils.LoggerPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;

public class UserAccountClientErrorHandler extends DefaultResponseErrorHandler {

    private final ObjectMapper objectMapper;

    public UserAccountClientErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());
    }

    //PortalAPIException
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        InputStream body = response.getBody();
        ErrorResponse errorResponse = objectMapper.readValue(body, ErrorResponse.class);

        int statusCode = response.getStatusCode().value();
        String errorMessage = errorResponse.getMessage();

        if (statusCode >= 400 && statusCode < 500) {
            throw new CustomException(HttpStatus.valueOf(statusCode), errorMessage);
        } else if (statusCode >= 500) {
            throw new CustomException(HttpStatus.valueOf(statusCode), ErrorMessage.INTERNAL_SERVER_ERROR);
        }
    }

}
