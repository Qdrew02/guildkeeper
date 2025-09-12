package com.guildkeeper.backend.controller;

import com.guildkeeper.backend.dao.users.UserDao;
import com.guildkeeper.backend.model.dto.auth.LoginRequest;
import com.guildkeeper.backend.model.dto.auth.LoginResponse;
import com.guildkeeper.backend.model.dto.auth.RegisterRequest;
import com.guildkeeper.backend.model.user.User;
import com.guildkeeper.backend.security.jwt.TokenProvider;
import com.guildkeeper.backend.service.RefreshTokenService;
import com.guildkeeper.backend.service.UserService;
import com.guildkeeper.backend.util.exception.DaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private RefreshTokenService refreshTokenService;
    private UserDao userDao;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao, RefreshTokenService refreshTokenService, TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.refreshTokenService = refreshTokenService;
    }

        @RequestMapping(path = "/login", method = RequestMethod.POST)
        public LoginResponse login(@Valid @RequestBody LoginRequest loginDto) {
            try {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

                if(authentication.isAuthenticated()){
                    String jwt = tokenProvider.createToken(authentication, false);
                    User user = userDao.getUserByUsername(loginDto.getUsername());
                    return new LoginResponse(jwt, user);
                }

                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
            catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
            }
        }

        @ResponseStatus(HttpStatus.CREATED)
        @RequestMapping(path = "/register", method = RequestMethod.POST)
        public User register(@Valid @RequestBody RegisterRequest newUser) {

            if(!newUser.isPasswordsMatch()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password and confirm password do not match");
            }

            try {
                if (userDao.getUserByUsername(newUser.getUsername()) != null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
                }

                User user = userDao.createUser(new User(newUser.getUsername(), newUser.getPassword(), newUser.getRole()));
                return user;
            }
            catch (DaoException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
            }
        }
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        return refreshTokenService.validateToken(refreshToken)
                .map(token -> {
                    // generate a new access token
                    String newAccessToken = tokenProvider.generateAccessToken(token.getUserId());
                    return ResponseEntity.ok(Map.of(
                            "accessToken", newAccessToken
                    ));
                })
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid or expired refresh token"));
    }
}
