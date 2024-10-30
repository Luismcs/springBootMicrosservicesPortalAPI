package com.exercise.authentication.service.impl;

import com.exercise.authentication.dto.RefreshTokenDTO;
import com.exercise.authentication.exception.CustomException;
import com.exercise.authentication.dto.JWTResponseDTO;
import com.exercise.authentication.dto.UserCredentialsDTO;
import com.exercise.authentication.exception.ErrorMessage;
import com.exercise.authentication.mapper.UserCredentialsMapper;
import com.exercise.authentication.model.RefreshToken;
import com.exercise.authentication.model.UserCredentials;
import com.exercise.authentication.repository.RefreshTokenRepository;
import com.exercise.authentication.repository.UserCredentialsRepository;
import com.exercise.authentication.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.exercise.authentication.utils.LoggerPrinter.printLog;
import static com.exercise.authentication.utils.PasswordUtil.*;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserCredentialsMapper userCredentialsMapper;
    private final JwtServiceImpl jwtService;

    public AuthenticationServiceImpl(UserCredentialsRepository userCredentialsRepository,
                                     UserCredentialsMapper userCredentialsMapper,
                                     JwtServiceImpl jwtService,
                                     RefreshTokenRepository refreshTokenRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userCredentialsMapper = userCredentialsMapper;
        this.jwtService = jwtService;
    }
    //Criar logger na class ou sl4j (lombok)

    public JWTResponseDTO signIn(UserCredentialsDTO userCredentialsDTO) {
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(userCredentialsDTO.getUsername());

        if (userCredentials == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BAD_CREDENTIALS);
        }

        if (!matches(userCredentialsDTO.getPassword(), userCredentials.getPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, ErrorMessage.BAD_CREDENTIALS);
        }

        String token = jwtService.generateToken(userCredentialsDTO.getUsername());
        String refreshToken = jwtService.generateRefreshToken(userCredentialsDTO.getUsername());
        saveRefreshToken(refreshToken, userCredentials);
        printLog("Token generated for user " + userCredentialsDTO.getUsername());
        return new JWTResponseDTO(token, refreshToken);
    }

    private void saveRefreshToken(String refreshToken, UserCredentials userCredentials) {
        RefreshToken savedRefreshToken = new RefreshToken();
        savedRefreshToken.setRefreshToken(refreshToken);
        savedRefreshToken.setUserCredentials(userCredentials);
        refreshTokenRepository.save(savedRefreshToken);
    }

    public UserCredentialsDTO signUp(UserCredentialsDTO userCredentialsDTO) {
        UserCredentials userCredentials = userCredentialsRepository.findByUsernameIgnoreCase(userCredentialsDTO.getUsername());
        if (userCredentials != null) {
            throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessage.USERNAME_ALREADY_EXISTS);
        }

        userCredentialsDTO.setPassword(hash(userCredentialsDTO.getPassword()));
        UserCredentials savedUserCredentials = userCredentialsRepository.save(userCredentialsMapper.toEntity(userCredentialsDTO));
        printLog("User created with id: " + savedUserCredentials.getId());
        return userCredentialsMapper.toDto(savedUserCredentials);
    }

    public JWTResponseDTO refreshToken(RefreshTokenDTO refreshTokenDTO) {

        RefreshToken refreshTokenFound = refreshTokenRepository.findByRefreshToken(refreshTokenDTO.getRefreshToken());

        //if present
        if (refreshTokenFound == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.REFRESH_TOKEN_NOT_FOUND);
        }

        if (!jwtService.isTokenExpired(refreshTokenDTO.getRefreshToken())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, ErrorMessage.REFRESH_TOKEN_STILL_VALID);
        }
        String refreshToken = jwtService.generateRefreshToken(refreshTokenFound.getUserCredentials().getUsername());
        saveRefreshToken(refreshToken, refreshTokenFound.getUserCredentials());

        return new JWTResponseDTO(jwtService.generateToken(refreshTokenFound.getUserCredentials().getUsername()),
                refreshToken);
    }

}
