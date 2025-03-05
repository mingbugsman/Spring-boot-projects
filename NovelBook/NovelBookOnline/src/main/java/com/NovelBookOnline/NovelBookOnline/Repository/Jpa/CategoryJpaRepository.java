package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, String> {
    @Query(value = """
            SELECT c.id FROM categories
            WHERE c.deleted_at IS NULL
            """, nativeQuery = true)
    List<String> getAllNonDeletedCategoryIds();

    @Query(value = """
            SELECT c FROM categories
            WHERE c.id IN :ids
            ORDER BY
                CASE WHEN :sortOrder = 'ASC' THEN n.created_at END ASC,
                CASE WHEN :sortOrder = 'DESC' THEN n.created_at END DESC
            """, nativeQuery = true)

    Page<Category> getAllNonDeletedCategoryByIds(@Param("sortOrder") String sortOrder, @Param("ids") List<String> ids, Pageable pageable);
    @Query(value = """
            SELECT c.id FROM novels n
            JOIN categories c
            WHERE n.id = :novelId AND n.deleted_at IS NULL AND c.deleted_at IS NULL
            """, nativeQuery = true)
    List<String> getCategoryIdsWithNovel(@Param("novelId") String novelId);

    @Query(value = """
            SELECT c FROM categories c
            WHERE c.category_name := categoryName AND c.deleted_at IS NULL
            """, nativeQuery = true)
    boolean existsByCategoryName(@Param("categoryName") String categoryName);

    @Query(value = """
            SELECT c FROM categories c
            WHERE c.id = :categoryId AND c.deleted_at IS NULL
            """, nativeQuery = true)
    Optional<Category> getNonDeletedCategory(@Param("categoryId") String categoryId);
}
