package com.exercise.manager.service;

import com.exercise.manager.dto.JWTResponseDTO;
import com.exercise.manager.dto.RefreshTokenDTO;
import com.exercise.manager.dto.UserCredentialsDTO;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public interface AuthenticationService {

    JWTResponseDTO signIn(UserCredentialsDTO userCredentialsDTO) throws IOException, ParseException;

    UserCredentialsDTO signUp(UserCredentialsDTO userCredentialsDTO) throws IOException, ParseException;

    JWTResponseDTO refreshToken(RefreshTokenDTO refreshTokenDTO) throws IOException, ParseException;

}
