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
    public void revokeAccessToken(String jwtId) {
        InvalidToken invalidToken = InvalidToken.builder()
                .jwtId(jwtId)
                .revocationDate(LocalDateTime.now())
                .build();
        invalidTokenJpaRepository.save(invalidToken);
    }

    @Override
    public boolean checkRevocationToken(String jwtId) {
        return invalidTokenJpaRepository.findRevokedToken(jwtId) != null;
    }
}
