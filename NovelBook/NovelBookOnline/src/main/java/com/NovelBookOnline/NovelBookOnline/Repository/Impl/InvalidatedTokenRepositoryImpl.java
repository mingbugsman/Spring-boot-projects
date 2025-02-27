package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.InvalidatedToken;
import com.NovelBookOnline.NovelBookOnline.Repository.IInvalidatedTokenRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.InvalidatedTokenJpaRepository;

public class InvalidatedTokenRepositoryImpl implements IInvalidatedTokenRepository {
    InvalidatedTokenJpaRepository invalidatedTokenJpaRepository;

    @Override
    public boolean existsById(String id) {
        return invalidatedTokenJpaRepository.existsById(id);
    }

    @Override
    public void save(InvalidatedToken invalidatedToken) {
        invalidatedTokenJpaRepository.save(invalidatedToken);
    }
}
