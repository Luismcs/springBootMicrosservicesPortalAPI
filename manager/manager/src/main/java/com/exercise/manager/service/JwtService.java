package com.exercise.manager.service;

import io.jsonwebtoken.Claims;

public interface JwtService {

    Claims extractAllClaims(String token);

    String extractUsername(String token);

    boolean isTokenExpired(String token);

}
