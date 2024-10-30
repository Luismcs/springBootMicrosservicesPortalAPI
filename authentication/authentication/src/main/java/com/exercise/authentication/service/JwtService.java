package com.exercise.authentication.service;

import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.util.Map;

public interface JwtService {

    String generateToken(String username);

    Claims extractAllClaims(String token);

    Map<String, Object> getClaims(String username);

    String generateRefreshToken(String username);

    boolean isTokenExpired(String token);

}
