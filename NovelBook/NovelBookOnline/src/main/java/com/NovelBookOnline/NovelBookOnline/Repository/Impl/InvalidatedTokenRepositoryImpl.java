package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.InvalidatedToken;
import com.NovelBookOnline.NovelBookOnline.Repository.IInvalidatedTokenRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.InvalidatedTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class InvalidatedTokenRepositoryImpl implements IInvalidatedTokenRepository {
    private final InvalidatedTokenJpaRepository invalidatedTokenJpaRepository;

    @Override
    public boolean existsById(String id) {
        return invalidatedTokenJpaRepository.existsById(id);
    }

    @Override
    public void save(InvalidatedToken invalidatedToken) {
        invalidatedTokenJpaRepository.save(invalidatedToken);
    }
}
