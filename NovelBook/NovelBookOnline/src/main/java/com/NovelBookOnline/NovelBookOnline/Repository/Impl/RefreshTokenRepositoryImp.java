package com.NovelBookOnline.NovelBookOnline.Repository.Impl;


import com.NovelBookOnline.NovelBookOnline.Entity.RefreshToken;

import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Repository.IRefreshTokenRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.RefreshTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImp implements IRefreshTokenRepository {
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenJpaRepository.findByToken(token).orElseThrow();
    }

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.save(refreshToken);
    }

    @Override
    public boolean existsByToken(String token) {
        return refreshTokenJpaRepository.existsByToken(token);
    }

    @Override
    public void deleteTokenByUser(User user) {
        refreshTokenJpaRepository.deleteByUser(user);
    }

    @Override
    public boolean checkExpiredRefreshToken(String token) {
        return refreshTokenJpaRepository.checkExpirationToken(token);
    }
}
