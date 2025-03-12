package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Category.CategoryRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.ApiResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategoryDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategorySummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Service.ICategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryDetailResponse> addCategory(@RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryDetailResponse>builder()
                .result(categoryService.addCategory(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryDetailResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryDetailResponse>builder()
                .result(categoryService.updateCategory(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ApiResponse.<String>builder()
                .result("Successfully deleted category id : " + id)
                .build();
    }

    @PostMapping("/{id}/novels")
    public ApiResponse<CategoryDetailResponse> addNovelsToCategory(
            @PathVariable String id, @RequestBody List<String> novelIds) {
        return ApiResponse.<CategoryDetailResponse>builder()
                .result(categoryService.addNovelsToCategory(id, novelIds))
                .build();
    }


    @GetMapping
    public ApiResponse<List<CategorySummaryResponse>> getAllCategories(
            @RequestParam(defaultValue = "ASC")SortOrder sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
            ) {
        return ApiResponse.<List<CategorySummaryResponse>>builder()
                .result(categoryService.getAllCategories(sortOrder, page, size))
                .build();
    }


}
