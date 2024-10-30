package com.exercise.authentication.controller;

import com.exercise.authentication.dto.JWTResponseDTO;
import com.exercise.authentication.dto.RefreshTokenDTO;
import com.exercise.authentication.dto.UserCredentialsDTO;
import com.exercise.authentication.service.impl.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticator")
public class AuthenticationController {

    private AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Authenticates the user and returns a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful, JWT token returned"),
            @ApiResponse(responseCode = "401", description = "Bad Credentials"),
            @ApiResponse(responseCode = "421", description = "Bad Credentials")

    })
    @PostMapping("/sign-in")
    public ResponseEntity<JWTResponseDTO> signIn(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        JWTResponseDTO jwtResponseDTO = authenticationService.signIn(userCredentialsDTO);
        return ResponseEntity.ok(jwtResponseDTO);
    }

    @Operation(summary = "Registers a new user and returns their credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered"),
            @ApiResponse(responseCode = "422", description = "User with that username already exists")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<UserCredentialsDTO> signUp(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        UserCredentialsDTO savedUserCredentials = authenticationService.signUp(userCredentialsDTO);
        return ResponseEntity.ok(savedUserCredentials);
    }

    @Operation(summary = "Renews the JWT token using a refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access and Refresh tokens successfully renewed"),
            @ApiResponse(responseCode = "404", description = "Refresh Token is still valid"),
            @ApiResponse(responseCode = "404", description = "Refresh Token not found")
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<JWTResponseDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        JWTResponseDTO jwtResponseDTO = authenticationService.refreshToken(refreshTokenDTO);
        return ResponseEntity.ok(jwtResponseDTO);
    }


}
