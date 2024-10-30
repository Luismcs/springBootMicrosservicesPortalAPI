package com.exercise.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {

    @Value("${secret-key.value}")
    private String secret;

    @Value("${user-account.service.url}")
    private String userAccountServiceUrl;

    @Value("${address.service.url}")
    private String addressServiceUrl;

    @Value("${authentication.service.url}")
    private String authenticationServiceUrl;

    public String getSecret() {
        return secret;
    }

    public String getUserAccountServiceUrl() {
        return userAccountServiceUrl;
    }

    public String getAuthenticationServiceUrl() {
        return authenticationServiceUrl;
    }
}
