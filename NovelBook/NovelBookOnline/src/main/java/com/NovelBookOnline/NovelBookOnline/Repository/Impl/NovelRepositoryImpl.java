package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import com.NovelBookOnline.NovelBookOnline.Repository.INovelRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.NovelJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Repository
public class NovelRepositoryImpl implements INovelRepository {
    private final NovelJpaRepository novelJpaRepository;

    @Override
    public Novel getNovelById(String id) {
        return novelJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Novel> getAllNovelsByPageAsc(LocalDateTime lastCreatedAt, Pageable pageable ) {
        return novelJpaRepository.getAllNovelByPageAsc(lastCreatedAt, pageable);
    }

    @Override
    public Page<Novel> getAllNovelsByPageDesc(LocalDateTime lastCreatedAt, Pageable pageable ) {
        return novelJpaRepository.getAllNovelByPageDesc(lastCreatedAt, pageable);
    }

    @Override
    public Novel save(Novel novel) {
        return novelJpaRepository.save(novel);
    }

    @Override
    public void deleteNovel(Novel novel) {
        novel.setDeletedAt(LocalDateTime.now());

    }

    @Override
    public boolean existsById(String id) {
        return novelJpaRepository.existsById(id);
    }
}
