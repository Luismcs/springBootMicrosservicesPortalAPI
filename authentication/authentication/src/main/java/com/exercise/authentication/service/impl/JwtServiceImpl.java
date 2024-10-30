package com.exercise.authentication.service.impl;

import com.exercise.authentication.config.ConfigProperties;
import com.exercise.authentication.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private final ConfigProperties configProperties;

    public JwtServiceImpl(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public String generateToken(String username) {
        return Jwts
                .builder()
                .claims(getClaims(username))
                .subject(username)
                .issuer(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + configProperties.getExpirationTime()))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
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

    public Map<String, Object> getClaims(String username) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", username);
        return extraClaims;
    }

    public String generateRefreshToken(String username) {
        return Jwts
                .builder()
                .claims(getClaims(username))
                .subject(username)
                .issuer(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + configProperties.getRefreshTokenExpirationTime()))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
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
