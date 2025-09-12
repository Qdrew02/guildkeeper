package com.guildkeeper.backend.service;

import com.guildkeeper.backend.security.RefreshToken;
import com.guildkeeper.backend.security.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;


@Service
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public RefreshTokenService(RefreshTokenRepository repository) {
        this.repository = repository;
    }

    public RefreshToken createToken(Long userId, String token, int daysValid, String deviceInfo) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(userId);
        refreshToken.setToken(token);
        refreshToken.setDeviceInfo(deviceInfo);
        refreshToken.setExpiresAt(Instant.now().plusSeconds(daysValid * 24L * 60 * 60));

        return repository.save(refreshToken);
    }

    public Optional<RefreshToken> validateToken(String token) {
        return repository.findByToken(token)
                .filter(rt -> rt.getExpiresAt().isAfter(Instant.now()));
    }

    public void deleteByToken(String token) {
        repository.deleteByToken(token);
    }

    public void deleteByUserId(Long userId) {
        repository.deleteByUserId(userId);
    }

    public Optional<Long> getUserIdFromValidToken(String token) {
        return validateToken(token).map(RefreshToken::getUserId);
    }

}