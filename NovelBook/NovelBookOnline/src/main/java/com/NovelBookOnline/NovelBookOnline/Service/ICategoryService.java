package com.NovelBookOnline.NovelBookOnline.Service;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Category.CategoryRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategoryDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategorySummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Category;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;

import java.util.List;

public interface ICategoryService {
    CategoryDetailResponse addCategory(CategoryRequest request);
    CategoryDetailResponse updateCategory(String id, CategoryRequest request);
    void deleteCategory(String id);
    CategoryDetailResponse addNovelsToCategory(String id, List<String> novelIds);
    List<CategorySummaryResponse> getAllCategories(SortOrder sortOrder, int page, int size);
}
