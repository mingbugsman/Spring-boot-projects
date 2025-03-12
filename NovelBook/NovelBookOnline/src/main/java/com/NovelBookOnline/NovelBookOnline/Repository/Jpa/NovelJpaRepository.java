package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface NovelJpaRepository extends JpaRepository<Novel,String> {


    @Query(value = """
            SELECT n.id FROM novels n
            JOIN users u
            WHERE u.username = :authorName AND n.novel_name = :novelName AND n.deleted_at IS NULL
            """, nativeQuery = true)
    String existsByAuthorIdAndNovelName(String authorName, String novelName);

    @Query(value = """
        SELECT DISTINCT n.id
        FROM novels n
        WHERE EXISTS (
            SELECT 1 FROM category_novel cn
            JOIN categories c ON cn.category_id = c.id
            WHERE cn.novel_id = n.id AND c.name IN :listCategoryName
        )
    """, nativeQuery = true)
    List<String> getAllIdsByListCategoryName(@Param("listCategoryName") List<String> listCategoryName);

    @Query(value = """
        SELECT n.id
        FROM novels n
        WHERE n.deleted_at IS NULL
          AND n.created_at >= NOW() - INTERVAL 1 DAY
    """, nativeQuery = true)
    List<String> getAllIdsByDaily();


    @Query(value = """
        SELECT n.id
        FROM novels n
        LEFT JOIN (
            SELECT lc.chapter_id, COUNT(*) AS total_likes
            FROM like_chapter lc
            JOIN like l ON lc.id = l.id AND l.like_status = 'UPVOTE'
            GROUP BY lc.chapter_id
        ) likes ON n.id = likes.chapter_id
        LEFT JOIN (
            SELECT novel_id, COUNT(*) AS total_chapters
            FROM chapter
            WHERE deleted IS NULL
            GROUP BY novel_id
            HAVING total_chapters < 50
        ) c ON n.id = c.novel_id
        WHERE n.deleted IS NULL
        GROUP BY n.id
        ORDER BY SUM(COALESCE(likes.total_likes, 0)) DESC, n.total_read DESC
        LIMIT 50
    """, nativeQuery = true)
    List<String> getAllIdsByLikeAndTotalReadChapter();



    @Query("""
        SELECT n.id FROM Novel n
        WHERE n.deletedAt IS NULL
        AND LOWER(n.novelName) LIKE LOWER(CONCAT('%', :keyword, '%'))
        ORDER BY n.createdAt DESC
    """)
    List<String> findNovelIdsByKeyword(@Param("keyword") String keyword);

    @Query(value = """
    SELECT *
    FROM novels n
    WHERE n.id IN :novelIds
    ORDER BY n.created_at DESC,
    """, nativeQuery = true)
    Page<Novel> findNovelsByIds(@Param("novelIds") List<String> novelIds, Pageable pageable);

    @Query(value = """
    SELECT * FROM novels n
    WHERE n.id IN :novelIds
    ORDER BY n.created_at DESC,
    """, nativeQuery = true)
    List<Novel> findNovelsByIds(@Param("novelIds") List<String> novelIds);


    @Query(value = """
            SELECT * FROM novels
            WHERE n.id = :novelId AND deleted_at IS NULL
            """, nativeQuery = true)
    Optional<Novel> findNovel(@Param("novelId") String novelId);

}
