package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.NovelBookOnline.NovelBookOnline.Entity.Chapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterJpaRepository extends JpaRepository<Chapter, String> {

    boolean existsByChapterNameAndChapterNumber(String chapterName, int chapterNumber);

    // GET SPECIFIC CHAPTER
    @Query(value = """
            SELECT c FROM chapters
             WHERE c.id = :id AND c.delete_at IS NULL""", nativeQuery = true)
    Optional<Chapter> getChapterById(@Param("id")String id);


    // GET ID BY BUSINESS

    // NOVEL ID
    @Query(value = """
            SELECT c.id FROM chapters c
             WHERE c.novel_id = :novelId AND c.deleted_at IS NULL
            """, nativeQuery = true)
    List<String> getAllIdsByNovelId(@Param("novelId") String novelId);

    // BY DAILY (NEW UPDATE)
    @Query("SELECT c.id FROM Chapter c WHERE c.deletedAt IS NULL AND c.createdAt >= :oneDayAgo")
    List<String> getAllIdsByDaily(@Param("oneDayAgo") LocalDateTime oneDayAgo);


    // BY TOP 10 HOTTEST CHAPTERS (base on first 12 days + total likes)
    @Query(value = """
        SELECT c.id FROM chapters c
        LEFT JOIN daily_read dr ON dr.chapter_id = c.id
        WHERE c.created_at <= :twelveDaysAgo
          AND c.deleted_at IS NULL
          AND dr.read_date BETWEEN DATE(c.created_at) AND DATE(c.created_at) + INTERVAL 11 DAY
        GROUP BY c.id
        ORDER BY COALESCE(SUM(dr.read_count), 0) + (SELECT COUNT(*) FROM like_chapter lc WHERE lc.chapter_id = c.id) DESC
        LIMIT 25
    """, nativeQuery = true)
    List<String> findTop25HottestChapterIds(@Param("twelveDaysAgo") LocalDateTime twelveDaysAgo);




    // GET NOVELS BY Ids
    @Query(value = """
            SELECT c FROM chapters
            WHERE c.id IN :ids
            ORDER BY c.created_at DESC
            """, nativeQuery = true)
    Page<Chapter> getChaptersByIds(@Param("ids") List<String> ids, Pageable pageable);

    @Query(value = """
            SELECT c FROM chapters
            WHERE c.id IN :ids
            ORDER BY c.created_at DESC
            """, nativeQuery = true)
    List<Chapter> getChaptersByIds(@Param("ids") List<String> ids);
}
