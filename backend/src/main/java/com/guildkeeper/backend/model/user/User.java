package com.guildkeeper.backend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
public class User {
    private long id;
    private String username;
    private String email;
    @JsonIgnore
    private String passwordHash;
    private String role;
    private boolean active;
    private LocalDateTime createdAt;

    //constructors
    public User() {};

    public User(int userId, String username, String email, String passwordHash, String role, LocalDateTime createdAt, boolean active) {
        this.id= userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
        this.active = active;
    }
    public User(String username, String passwordHash, String role) {
        this(0, username, passwordHash, role);
    }

    public User(int i, String username, String passwordHash, String role) {
    }


    //getters and setters

    public long getUserId() {
        return id;
    }

    public void setUserId(int userId) {
        this.id = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
