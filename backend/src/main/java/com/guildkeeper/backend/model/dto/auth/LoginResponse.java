package com.guildkeeper.backend.model.dto.auth;

import com.guildkeeper.backend.model.user.User;

public class LoginResponse {
    private String token;
    private User user;
    private String username;

    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
