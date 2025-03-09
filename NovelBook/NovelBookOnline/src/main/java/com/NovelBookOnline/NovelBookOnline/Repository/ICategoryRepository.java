package com.NovelBookOnline.NovelBookOnline.Repository;

import com.NovelBookOnline.NovelBookOnline.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryRepository {
    void save(Category category);
    boolean existsById(String id);
    boolean existsByCategoryName(String categoryName);
    Category getCategory(String id);
    Page<Category> getCategories(String sortOrder, Pageable pageable);
    void delete(Category category);
}
