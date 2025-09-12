package com.guildkeeper.backend.model.dto.auth;

import com.guildkeeper.backend.model.user.User;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private User user;

    public LoginResponse(String accessToken, String refreshToken, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    // getters & setters
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
