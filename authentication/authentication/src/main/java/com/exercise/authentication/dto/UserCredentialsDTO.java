package com.exercise.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public class UserCredentialsDTO extends AbstractDTO {

    @Schema(description = "The user credential's username",
            example = "john_doe",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @Schema(description = "The user credential's password",
            example = "Pa55w0rd123",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public UserCredentialsDTO(Long id, String createdBy, Date createdDate, String lastModifiedBy, Date lastModifiedDate, String username, String password) {
        super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
