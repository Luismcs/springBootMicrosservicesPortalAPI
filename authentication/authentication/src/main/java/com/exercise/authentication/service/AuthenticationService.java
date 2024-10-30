package com.exercise.authentication.service;

import com.exercise.authentication.dto.JWTResponseDTO;
import com.exercise.authentication.dto.RefreshTokenDTO;
import com.exercise.authentication.dto.UserCredentialsDTO;

public interface AuthenticationService {

    JWTResponseDTO signIn(UserCredentialsDTO userCredentialsDTO);

    UserCredentialsDTO signUp(UserCredentialsDTO userCredentialsDTO);

    JWTResponseDTO refreshToken(RefreshTokenDTO refreshTokenDTO);

}
