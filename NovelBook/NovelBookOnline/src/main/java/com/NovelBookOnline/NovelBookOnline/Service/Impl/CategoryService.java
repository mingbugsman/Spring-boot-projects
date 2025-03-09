package com.NovelBookOnline.NovelBookOnline.Service.Impl;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Category.CategoryRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategoryDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategorySummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Category;
import com.NovelBookOnline.NovelBookOnline.Entity.Novel;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Exception.ApplicationException;
import com.NovelBookOnline.NovelBookOnline.Exception.ErrorCode;
import com.NovelBookOnline.NovelBookOnline.Mapper.CategoryMapper;
import com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper.CustomerMappingHelper;
import com.NovelBookOnline.NovelBookOnline.Repository.ICategoryRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.INovelRepository;
import com.NovelBookOnline.NovelBookOnline.Service.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {

    ICategoryRepository categoryRepository;
    INovelRepository novelRepository;
    CategoryMapper categoryMapper;
    CustomerMappingHelper customerMappingHelper;
    @Override
    public CategoryDetailResponse addCategory(CategoryRequest request) {
        if (categoryRepository.existsByCategoryName(request.getCategoryName())) {
            throw new ApplicationException(ErrorCode.CATEGORY_EXISTED);
        }
        Category category = categoryMapper.toEntity(request);
        categoryRepository.save(category);
        return null;
    }

    @Override
    public CategoryDetailResponse updateCategory(String id, CategoryRequest request) {
        if(!categoryRepository.existsById(id)) {
            throw new ApplicationException(ErrorCode.CATEGORY_NOT_EXISTED);
        }

        Category category = categoryRepository.getCategory(id);
        categoryMapper.updateEntity(category, request);
        categoryRepository.save(category);
        return customerMappingHelper.toCategoryDetailResponse(category);
    }

    @Override
    public void deleteCategory(String id) {
        Category category = categoryRepository.getCategory(id);
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDetailResponse addNovelsToCategory(String id, List<String> novelIds) {
        Category category = categoryRepository.getCategory(id);
        for (String novelId : novelIds) {
            category.getNovels().add(novelRepository.findNovelById(novelId));
        }
        categoryRepository.save(category);
        return customerMappingHelper.toCategoryDetailResponse(category);
    }

    @Override
    public List<CategoryDetailResponse> getAllCategories(SortOrder sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.getCategories(sortOrder.name(), pageable).stream()
                .map(customerMappingHelper::toCategoryDetailResponse)
                .toList();
    }

}
