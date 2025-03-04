package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Like;
import com.NovelBookOnline.NovelBookOnline.Repository.ILikeRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.LikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements ILikeRepository {

    LikeJpaRepository likeJpaRepository;

    @Override
    public void likeChapter(Like like) {
        likeJpaRepository.save(like);
    }

    @Override
    public void unLikeChapter(Like like) {
        likeJpaRepository.delete(like);
    }
}
