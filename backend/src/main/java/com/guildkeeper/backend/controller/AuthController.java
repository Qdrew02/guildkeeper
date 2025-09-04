package com.guildkeeper.backend.controller;

import com.guildkeeper.backend.model.dto.auth.LoginRequest;
import com.guildkeeper.backend.model.dto.auth.LoginResponse;
import com.guildkeeper.backend.model.dto.auth.RegisterRequest;
import com.guildkeeper.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest registerRequest) {
        LoginResponse response = userService.registerUser(registerRequest);
        return ResponseEntity.ok(response);
    }
}
