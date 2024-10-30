package com.exercise.manager.controller;

import com.exercise.manager.dto.JWTResponseDTO;
import com.exercise.manager.dto.RefreshTokenDTO;
import com.exercise.manager.dto.UserCredentialsDTO;
import com.exercise.manager.service.impl.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/authenticator")
public class AuthenticationController {

    AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    //Exceções no handler
    @Operation(summary = "Authenticates the user and returns a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful, JWT token returned"),
            @ApiResponse(responseCode = "401", description = "Bad Credentials"),
            @ApiResponse(responseCode = "421", description = "Bad Credentials")

    })
    @PostMapping("/sign-in")
    public ResponseEntity<JWTResponseDTO> signIn(@RequestBody @Valid UserCredentialsDTO userCredentialsDTO)
            throws IOException, ParseException {
        JWTResponseDTO jwtResponseDTO = authenticationService.signIn(userCredentialsDTO);
        return ResponseEntity.ok(jwtResponseDTO);
    }

    @Operation(summary = "Registers a new user and returns their credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered"),
            @ApiResponse(responseCode = "422", description = "User with that username already exists")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<UserCredentialsDTO> signUp(@RequestBody @Valid UserCredentialsDTO userCredentialsDTO)
            throws IOException, ParseException {
        UserCredentialsDTO savedUserCredentials = authenticationService.signUp(userCredentialsDTO);
        return ResponseEntity.ok(savedUserCredentials);
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Renews the JWT token using a refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access and Refresh tokens successfully renewed"),
            @ApiResponse(responseCode = "404", description = "Refresh Token is still valid"),
            @ApiResponse(responseCode = "404", description = "Refresh Token not found")
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<JWTResponseDTO> refreshToken(@RequestBody @Valid RefreshTokenDTO refreshTokenDTO)
            throws IOException, ParseException {
        JWTResponseDTO jwtResponseDTO = authenticationService.refreshToken(refreshTokenDTO);
        return ResponseEntity.ok(jwtResponseDTO);
    }

}
