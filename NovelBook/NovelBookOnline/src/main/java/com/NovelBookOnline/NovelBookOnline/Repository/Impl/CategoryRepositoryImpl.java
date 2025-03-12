package com.NovelBookOnline.NovelBookOnline.Repository.Impl;

import com.NovelBookOnline.NovelBookOnline.Entity.Category;
import com.NovelBookOnline.NovelBookOnline.Exception.ApplicationException;
import com.NovelBookOnline.NovelBookOnline.Exception.ErrorCode;
import com.NovelBookOnline.NovelBookOnline.Repository.ICategoryRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.Jpa.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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

        return getCategory(id) != null;
    }

    @Override
    public boolean existsByCategoryName(String categoryName) {
        return categoryJpaRepository.existsByCategoryName(categoryName) != null;
    }

    @Override
    public Category getCategory(String id) {
        return categoryJpaRepository.getNonDeletedCategory(id).orElseThrow(() -> new ApplicationException(ErrorCode.CATEGORY_NOT_EXISTED));
    }

    @Override
    public Page<Category> getCategories(String sortOrder, Pageable pageable) {
        List<String> ids= categoryJpaRepository.getAllNonDeletedCategoryIds();
        System.out.println("total ids :::" + ids.size());
        return categoryJpaRepository.getAllNonDeletedCategoryByIds(sortOrder, ids, pageable);
    }



    @Override
    public void delete(Category category) {
        category.setDeleteAt(LocalDateTime.now());
        categoryJpaRepository.save(category);
    }

}
