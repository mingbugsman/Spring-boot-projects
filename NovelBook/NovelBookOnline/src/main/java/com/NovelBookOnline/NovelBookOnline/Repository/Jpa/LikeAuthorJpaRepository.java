package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeAuthorJpaRepository extends JpaRepository<LikeAuthor, String> {
    LikeAuthor findByUserIdAndAuthorId(String userId, String authorId);
}
