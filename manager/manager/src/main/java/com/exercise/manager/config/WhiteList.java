package com.exercise.manager.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WhiteList {

    private static final String[] WHITELIST_ENDPOINTS = {
            "/authenticator/sign-in",
            "/authenticator/sign-up",
            "/authenticator/refresh-token",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    public String[] getWhitelistEndpoints() {
        return WHITELIST_ENDPOINTS;
    }
}
