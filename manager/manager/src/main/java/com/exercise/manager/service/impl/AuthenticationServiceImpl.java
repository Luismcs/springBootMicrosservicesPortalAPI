package com.exercise.manager.service.impl;

import com.exercise.manager.client.AuthenticationClient;
import com.exercise.manager.dto.JWTResponseDTO;
import com.exercise.manager.dto.RefreshTokenDTO;
import com.exercise.manager.dto.UserCredentialsDTO;
import com.exercise.manager.service.AuthenticationService;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    AuthenticationClient authorizationClient;

    public AuthenticationServiceImpl(AuthenticationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    @Override
    public JWTResponseDTO signIn(UserCredentialsDTO userCredentialsDTO) throws IOException, ParseException {
        return authorizationClient.signIn(userCredentialsDTO);
    }

    @Override
    public UserCredentialsDTO signUp(UserCredentialsDTO userCredentialsDTO) throws IOException, ParseException {
        return authorizationClient.signUp(userCredentialsDTO);
    }

    @Override
    public JWTResponseDTO refreshToken(RefreshTokenDTO refreshTokenDTO) throws IOException, ParseException {
        return authorizationClient.refreshToken(refreshTokenDTO);
    }

}
