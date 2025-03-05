package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NovelJpaRepository extends JpaRepository<Novel,String> {

    boolean existsById(String id);

    @Query(value = """
            SELECT 1 FROM novels n
            JOIN users u
            WHERE u.username = :authorName AND n.novel_name = :novelName
            """, nativeQuery = true)
    boolean existsByAuthorIdAndNovelName(String authorName, String novelName);

    @Query(value = """
            SELECT n.id FROM novels
            WHERE novels.deleted_at IS NULL
            """, nativeQuery = true)
    List<String> getAllIds();

    @Query("""
        SELECT n.id FROM Novel n
        WHERE n.deletedAt IS NULL
        AND LOWER(n.novelName) LIKE LOWER(CONCAT('%', :keyword, '%'))
        ORDER BY n.createdAt DESC
    """)
    Page<String> findNovelIdsByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("""
    SELECT n FROM Novel n
    WHERE n.id IN :novelIds
    ORDER BY
        CASE WHEN :sortOrder = 'ASC' THEN n.createdAt END ASC,
        CASE WHEN :sortOrder = 'DESC' THEN n.createdAt END DESC
    """)
    List<Novel> findNovelsByIds(@Param("sortOrder") String sortOrder,@Param("novelIds") List<String> novelIds);

    @Query(value = """
            SELECT n FROM novels
            WHERE n.id = :novelId AND deleted_at IS NULL
            """, nativeQuery = true)
    Optional<Novel> findNovel(@Param("novelId") String novelId);

}
