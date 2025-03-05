package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Category.CategoryRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategoryDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategorySummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Enum.SortOrder;
import com.NovelBookOnline.NovelBookOnline.Service.ICategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")

public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDetailResponse> addCategory(@RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDetailResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/novels")
    public ResponseEntity<CategoryDetailResponse> addNovelsToCategory(
            @PathVariable String id, @RequestBody List<String> novelIds) {
        return ResponseEntity.ok(categoryService.addNovelsToCategory(id, novelIds));
    }

    // nên chuyển qua categories service !!!!!
    @GetMapping("/novels/{novelId}")
    public ResponseEntity<List<CategorySummaryResponse>> NovelWithCategories(@PathVariable String novelId) {
        return ResponseEntity.ok(categoryService.NovelWithCategories(novelId));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDetailResponse>> getAllCategories(
            @RequestParam(defaultValue = "ASC")SortOrder sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        return ResponseEntity.ok(categoryService.getAllCategories(sortOrder, page, size));
    }
}
