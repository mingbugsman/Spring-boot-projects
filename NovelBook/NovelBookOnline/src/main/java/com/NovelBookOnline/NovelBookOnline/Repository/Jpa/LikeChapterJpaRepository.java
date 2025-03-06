package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeChapterJpaRepository extends JpaRepository<LikeChapter, String> {
    LikeChapter findByUserIdAndChapterId(String userId, String chapterId);
}
