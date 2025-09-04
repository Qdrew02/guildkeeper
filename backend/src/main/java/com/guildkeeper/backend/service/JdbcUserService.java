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

@Lazy
@Service
public class JdbcUserService implements UserService{
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public JdbcUserService(UserDao userDao,
                       PasswordEncoder passwordEncoder,
                       TokenProvider tokenProvider,
                       AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    // Registration method
    public LoginResponse registerUser(RegisterRequest registerRequest) {
        // Create a new User object
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPasswordHash(registerRequest.getPassword());
        newUser.setRole(registerRequest.getRole() != null ? registerRequest.getRole() : "USER");

        // Persist user in the database
        User createdUser;
        try {
            createdUser = userDao.createUser(newUser);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to create user", e);
        }

        // Automatically authenticate after registration
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getUsername(),
                        registerRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT
        String token = tokenProvider.createToken(authentication, false);

        return new LoginResponse(token, createdUser.getUsername());
    }

    // Login method
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication, false);
        return new LoginResponse(token, loginRequest.getUsername());
    }
}
