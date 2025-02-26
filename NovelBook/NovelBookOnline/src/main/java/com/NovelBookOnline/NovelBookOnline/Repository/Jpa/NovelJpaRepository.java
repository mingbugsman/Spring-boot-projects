package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface NovelJpaRepository extends JpaRepository<Novel,String> {
    boolean existsById(String id);

    @Query("""
        SELECT n FROM Novel n
        WHERE n.deletedAt IS NULL
        AND (:lastCreatedAt IS NULL OR n.createdAt > :lastCreatedAt)
        ORDER BY n.createdAt ASC
        """)
    Page<Novel> getAllNovelByPageAsc(
            @Param("lastCreatedAt") LocalDateTime lastCreatedAt,
            Pageable pageable
    );

    @Query("""
        SELECT n FROM Novel n
        WHERE n.deletedAt IS NULL
        AND (:lastCreatedAt IS NULL OR n.createdAt < :lastCreatedAt)
        ORDER BY n.createdAt DESC
        """)
    Page<Novel> getAllNovelByPageDesc(
            @Param("lastCreatedAt") LocalDateTime lastCreatedAt,
            Pageable pageable
    );

}
