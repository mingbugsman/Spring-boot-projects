package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Like.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeCommentJpaRepository extends JpaRepository<LikeComment, String> {
    LikeComment findByUserIdAndCommentId(String userId, String commentId);

}
