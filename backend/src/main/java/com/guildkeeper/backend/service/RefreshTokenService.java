package com.guildkeeper.backend.service;

import com.guildkeeper.backend.security.RefreshToken;
import com.guildkeeper.backend.security.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;


public interface RefreshTokenService {


    RefreshToken createToken(Long userId, String token, int daysValid);

    Optional<RefreshToken> validateToken(String token);

    void deleteByToken(String token);

    void deleteByUserId(Long userId);

    Optional<Long> getUserIdFromValidToken(String token);
}