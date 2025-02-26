package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface INovelRepository {
    Novel getNovelById(String id);
    Page<Novel> getAllNovelsByPageAsc(LocalDateTime lastCreatedAt, Pageable pageable);
    Page<Novel> getAllNovelsByPageDesc(LocalDateTime lastCreatedAt, Pageable pageable);
    Novel save(Novel novel);
    void deleteNovel(Novel novel);
    boolean existsById(String id);
}
