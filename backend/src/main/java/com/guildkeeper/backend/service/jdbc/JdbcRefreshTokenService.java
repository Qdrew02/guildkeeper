package com.guildkeeper.backend.service.jdbc;

import com.guildkeeper.backend.security.RefreshToken;
import com.guildkeeper.backend.security.RefreshTokenRepository;
import com.guildkeeper.backend.service.RefreshTokenService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class JdbcRefreshTokenService implements RefreshTokenService {

    private final RefreshTokenRepository repository;

    public JdbcRefreshTokenService(RefreshTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public RefreshToken createToken(Long userId, String token, int daysValid) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(userId);
        refreshToken.setToken(token);
        refreshToken.setExpiresAt(Instant.now().plusSeconds(daysValid * 24L * 60 * 60));

        return repository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> validateToken(String token) {
        return repository.findByToken(token)
                .filter(rt -> rt.getExpiresAt().isAfter(Instant.now()));
    }

    @Override
    public void deleteByToken(String token) {
        repository.deleteByToken(token);
    }

    @Override
    public void deleteByUserId(Long userId) {
        repository.deleteByUserId(userId);
    }

    @Override
    public Optional<Long> getUserIdFromValidToken(String token) {
        return validateToken(token).map(RefreshToken::getUserId);
    }

}
