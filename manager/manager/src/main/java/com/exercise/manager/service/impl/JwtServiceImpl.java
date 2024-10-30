package com.exercise.manager.service.impl;

import com.exercise.manager.config.ConfigProperties;
import com.exercise.manager.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtServiceImpl implements JwtService {

    private final ConfigProperties configProperties;

    public JwtServiceImpl(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(configProperties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getIssuer();
    }

    public boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token).getExpiration();
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

}
