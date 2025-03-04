package com.NovelBookOnline.NovelBookOnline.Repository.Jpa;

import com.NovelBookOnline.NovelBookOnline.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, String> {

    @Query(value = """
            SELECT c.id FROM categories c
            """,nativeQuery = true)
    List<String> getAllCategoryIds();


    @Query(value = """
            SELECT c.id FROM novels n
            JOIN categories c
            WHERE n.id = :novelId
            """, nativeQuery = true)
    List<String> getCategoryIdsWithNovel(@Param("novelId") String novelId);

    boolean existsByCategoryName(String categoryName);
}
