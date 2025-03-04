package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Category;
import com.NovelBookOnline.NovelBookOnline.Repository.ICategoryRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements ICategoryRepository {
    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public void save(Category category) {
        categoryJpaRepository.save(category);
    }

    @Override
    public boolean existsById(String id) {

        return categoryJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByCategoryName(String categoryName) {
        return categoryJpaRepository.existsByCategoryName(categoryName);
    }

    @Override
    public Category getCategory(String id) {
        return categoryJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Category> getCategories() {
        List<String> ids= categoryJpaRepository.getAllCategoryIds();
        return categoryJpaRepository.findAllById(ids).stream().toList();
    }

    @Override
    public Category getNovelsByCategoryId(String id) {
        return categoryJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Category> getCategoriesByNovelId(String novelId) {
        List<String> ids = categoryJpaRepository.getCategoryIdsWithNovel(novelId);
        return categoryJpaRepository.findAllById(ids).stream().toList();
    }

    @Override
    public void delete(Category category) {
        category.setDeleteAt(LocalDateTime.now());
    }

}
