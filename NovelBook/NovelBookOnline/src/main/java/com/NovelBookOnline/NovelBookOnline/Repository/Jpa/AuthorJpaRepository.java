package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorJpaRepository extends JpaRepository<Author, String> {
    @Query(value = """
            SELECT a.id FROM authors a
            WHERE a.deleted_at IS NULL
            """, nativeQuery = true)
    List<String> getAllAuthorIds(String sortOrder);

    @Query(value = """
            SELECT a FROM Author a
            WHERE a.deletedAt IS NULL
            AND LOWER(a.authorName) LIKE LOWER(CONCAT('%', :keyword, '%'))
            ORDER BY
                 CASE WHEN :sortOrder = 'ASC' THEN a.createdAt END ASC,
                 CASE WHEN :sortOrder = 'DESC' THEN a.createdAt END DESC
            """)
    Page<Author> findAuthorsByKeyWord(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = """
            SELECT * FROM authors a
            WHERE a.deleted_at IS NULL AND a.id = :authorId
            """, nativeQuery = true)
    Optional<Author> getAuthor(@Param("authorId") String authorId);

    @Query(value = """
        SELECT a.id
        FROM authors a
        LEFT JOIN like_author la ON a.id = la.author_id
        WHERE a.deleted_at IS NULL
        GROUP BY a.id
        ORDER BY
            CASE WHEN :sortOrder = 'ASC' THEN COUNT(la.id) ASC
            CASE WHEN :sortOrder = 'DESC' THEN COUNT(la.id) DESC
    """, nativeQuery = true)
    List<String> findTopAuthorsByLike(@Param("sortOrder") String sortOrder);

    @Query(value = "SELECT a FROM authors " +
            "WHERE a.id IN ids", nativeQuery = true)
    Page<Author> getAllAuthorsByIds(List<String> ids, Pageable pageable);
}
