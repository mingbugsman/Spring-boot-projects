package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeJpaRepository extends JpaRepository<Like,String> {
    Optional<Like> findByUserIdAndChapterId(String userId, String chapterId);
}
