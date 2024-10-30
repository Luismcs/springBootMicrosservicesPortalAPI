package com.exercise.authentication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserCredentials extends AbstractEntity {

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userCredentials", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<RefreshToken> refreshTokens;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RefreshToken> getRefreshTokens() {
        return refreshTokens;
    }

    public void setRefreshTokens(List<RefreshToken> refreshTokens) {
        this.refreshTokens = refreshTokens;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
