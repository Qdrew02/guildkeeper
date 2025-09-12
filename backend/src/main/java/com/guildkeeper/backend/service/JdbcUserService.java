package com.guildkeeper.backend.service;

import com.guildkeeper.backend.dao.users.UserDao;
import com.guildkeeper.backend.model.dto.auth.LoginRequest;
import com.guildkeeper.backend.model.dto.auth.LoginResponse;
import com.guildkeeper.backend.model.dto.auth.RegisterRequest;
import com.guildkeeper.backend.model.user.User;
import com.guildkeeper.backend.security.jwt.TokenProvider;
import com.guildkeeper.backend.util.exception.DaoException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Lazy
@Service
public class JdbcUserService implements UserService{
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;


    public JdbcUserService(UserDao userDao,
                       PasswordEncoder passwordEncoder,
                       TokenProvider tokenProvider,
                       AuthenticationManager authenticationManager,
                       RefreshTokenService refreshTokenService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    // Registration method
    public LoginResponse registerUser(RegisterRequest registerRequest) {
        // 1️⃣ Create a new User object
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPasswordHash(registerRequest.getPassword());
        newUser.setRole(registerRequest.getRole() != null ? registerRequest.getRole() : "USER");

        // 2️⃣ Persist user in the database
        User createdUser;
        try {
            createdUser = userDao.createUser(newUser);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to create user", e);
        }

        // 3️⃣ Automatically authenticate after registration
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getUsername(),
                        registerRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 4️⃣ Generate access token
        String accessToken = tokenProvider.createToken(authentication, false);

        // 5️⃣ Generate refresh token and store in DB
        String refreshTokenStr = UUID.randomUUID().toString(); // raw token
        refreshTokenService.createToken(createdUser.getUserId(), refreshTokenStr, 30); // 30 days

        // 6️⃣ Return full LoginResponse
        return new LoginResponse(accessToken, refreshTokenStr, createdUser);
    }


    // Login method
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        String accessToken = tokenProvider.createToken(authentication, false);

        User user = userDao.getUserByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("Authenticated user not found in DB");
        }

        String refreshTokenStr = UUID.randomUUID().toString(); // simple raw token
        refreshTokenService.createToken(user.getUserId(), refreshTokenStr, 30); // 30 days

        return new LoginResponse(accessToken, refreshTokenStr, user);
    }

    @Override
    public String getUsernameById(Long userId) {
        return null;
    }
}
