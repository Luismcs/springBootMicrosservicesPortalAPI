package com.exercise.manager.client;

import com.exercise.manager.config.ConfigProperties;
import com.exercise.manager.dto.JWTResponseDTO;
import com.exercise.manager.dto.RefreshTokenDTO;
import com.exercise.manager.dto.UserCredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class AuthenticationClient {

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String url;

    public AuthenticationClient(ConfigProperties configProperties) {
        this.httpClient = HttpClientBuilder.create().build();
        this.objectMapper = new ObjectMapper();
        this.url = configProperties.getAuthenticationServiceUrl();
    }

    public JWTResponseDTO signIn(UserCredentialsDTO userCredentialsDTO) throws IOException, ParseException {
        HttpPost httpPost = new HttpPost(url + "/sign-in");
        String jsonPayload = objectMapper.writeValueAsString(userCredentialsDTO);
        StringEntity entity = new StringEntity(jsonPayload, StandardCharsets.UTF_8);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return objectMapper.readValue(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8), JWTResponseDTO.class);
    }

    public UserCredentialsDTO signUp(UserCredentialsDTO userCredentialsDTO) throws IOException, ParseException {
        HttpPost httpPost = new HttpPost(url + "/sign-up");
        String jsonPayload = objectMapper.writeValueAsString(userCredentialsDTO);
        StringEntity entity = new StringEntity(jsonPayload, StandardCharsets.UTF_8);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return objectMapper.readValue(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8), UserCredentialsDTO.class);
    }

    public JWTResponseDTO refreshToken(RefreshTokenDTO refreshTokenDTO) throws IOException, ParseException {
        HttpPost httpPost = new HttpPost(url + "/refresh-token");
        String jsonPayload = objectMapper.writeValueAsString(refreshTokenDTO);
        StringEntity entity = new StringEntity(jsonPayload, StandardCharsets.UTF_8);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return objectMapper.readValue(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8), JWTResponseDTO.class);
    }

}
