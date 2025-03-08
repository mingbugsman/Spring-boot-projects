package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;

@Repository
public interface ChapterJpaRepository extends JpaRepository<Chapter, String> {
    @Query("""
            SELECT c FROM chapters
             WHERE c.id = :id AND c.delete_at IS NULL""")
    Chapter getChapterById(@Param("id")String id);
}
