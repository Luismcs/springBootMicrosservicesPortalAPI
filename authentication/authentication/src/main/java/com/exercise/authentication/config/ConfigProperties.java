package com.exercise.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {

    @Value("${secret-key.value}")
    private String secret;

    @Value("${expiration-time.value}")
    private long expirationTime;

    @Value("${refresh-token-expiration-time.value}")
    private long refreshTokenExpirationTime;

    public String getSecret() {
        return secret;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public long getRefreshTokenExpirationTime() {
        return refreshTokenExpirationTime;
    }
}
