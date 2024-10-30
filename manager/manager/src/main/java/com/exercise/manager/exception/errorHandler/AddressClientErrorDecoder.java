package com.exercise.manager.exception.errorHandler;

import com.exercise.manager.exception.CustomException;
import com.exercise.manager.exception.ErrorMessage;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AddressClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.body() != null) {

            String responseBody = getErrorResponseToString(response);

            if (response.status() == 404) {
                if (responseBody.contains("user")) {
                    throw new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ACCOUNT_NOT_FOUND);
                } else {
                    throw new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ADDRESS_NOT_FOUND);
                }
            }
            return new Exception("Generic error");
        }

        return null;

    }

    private String getErrorResponseToString(Response response) {
        try {
            return Util.toString(response.body().asReader(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
