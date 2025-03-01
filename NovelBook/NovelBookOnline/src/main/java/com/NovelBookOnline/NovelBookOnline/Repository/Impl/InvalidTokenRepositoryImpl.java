package com.NovelBookOnline.NovelBookOnline.Repository.Impl;


import com.NovelBookOnline.NovelBookOnline.Entity.InvalidToken;
import com.NovelBookOnline.NovelBookOnline.Repository.IInvalidTokenRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.InvalidTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class InvalidTokenRepositoryImpl  implements IInvalidTokenRepository {
    private final InvalidTokenJpaRepository invalidTokenJpaRepository;

    @Override
    public void revokeAccessToken(String token) {
        InvalidToken invalidToken = InvalidToken.builder()
                .accessToken(token)
                .revocationDate(LocalDateTime.now())
                .build();
        invalidTokenJpaRepository.save(invalidToken);
    }

    @Override
    public boolean checkRevocationToken(String token) {
        return invalidTokenJpaRepository.findRevokedToken(token) != null;
    }
}
