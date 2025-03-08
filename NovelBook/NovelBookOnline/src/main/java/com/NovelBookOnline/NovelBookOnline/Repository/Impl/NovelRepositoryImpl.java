package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Repository.INovelRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.NovelJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NovelRepositoryImpl implements INovelRepository {
    private final NovelJpaRepository novelJpaRepository;


    @Override
    public Page<Novel> findNovelsByKeyword(String keyword, Pageable pageable) {
        List<String> ids =  novelJpaRepository.findNovelIdsByKeyword(keyword);
        return novelJpaRepository.findNovelsByIds(ids, pageable);

    }

    @Override
    public Page<Novel> getNewUpdateNovels(Pageable pageable) {
        List<String> ids = novelJpaRepository.getAllIdsByDaily();
        return novelJpaRepository.findNovelsByIds(ids, pageable);
    }



    @Override
    public List<Novel> getTrendingNovel() {
        List<String> ids =  novelJpaRepository.getAllIdsByLikeAndTotalReadChapter();
        return novelJpaRepository.findNovelsByIds(ids);
    }


    @Override
    public Novel findNovelById(String id) {
        return novelJpaRepository.findNovel(id).orElseThrow();
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

    @Override
    public boolean existsByAuthorIdAndNovelName(String authorId, String novelName) {
        return novelJpaRepository.existsByAuthorIdAndNovelName(authorId, novelName);
    }
}
