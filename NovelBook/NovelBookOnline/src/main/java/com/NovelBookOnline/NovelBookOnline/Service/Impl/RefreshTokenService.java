package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.RefreshToken;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Repository.IRefreshTokenRepository;
import com.NovelBookOnline.NovelBookOnline.Service.IRefreshTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RefreshTokenService implements IRefreshTokenService {
    IRefreshTokenRepository refreshTokenRepository;

    @NonFinal
    @Value("${jwt.refreshTokenExpirationMs}")
    protected long refreshTokenExpirationMs;

    @Override
    public RefreshToken generateRefreshToken(User user) {
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String hashedToken = encoder.encode(UUID.randomUUID().toString());
        RefreshToken refreshToken = RefreshToken.builder()
                .token(hashedToken)
                .expirationTime(new Date(Instant.now().plus(refreshTokenExpirationMs, ChronoUnit.SECONDS).toEpochMilli()))
                .user(user)
                .build();
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public void removeRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token);
        User user = refreshToken.getUser();
        refreshTokenRepository.deleteTokenByUser(user);
    }

    @Override
    public RefreshToken verifyToken(String requestToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(requestToken);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean isValid = encoder.matches(requestToken, requestToken);

        if ( isValid || refreshToken.getExpirationTime().before(new Date())) {
            throw new RuntimeException("invalid or Revoked Refresh Token");
        }
        return refreshToken;
    }

}
