package com.PhoneInventoryManager.PhoneInventory.Controller;


import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category.CategoryDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Request.CategoryRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category.CategoryStockDTO;
import com.PhoneInventoryManager.PhoneInventory.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j

public class CategoryController {
    CategoryService categoryService;


    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String category_id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(category_id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @GetMapping("/CategoriesPhoneCount")
    public ResponseEntity<List<CategoryStockDTO>> getCategoriesWithPhoneCount() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoriesWithPhoneCount());
    }
    @GetMapping("/CategoriesPhoneStock")
    public ResponseEntity<List<CategoryStockDTO>> getCategoriesWithPhoneStock( ) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getStockByCategory());
    }

    @GetMapping("/CategoryWithPhoneCount/{categoryName}")
    public ResponseEntity<CategoryStockDTO> getCategoryWithPhoneCount(@PathVariable String categoryName) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryWithPhoneCount(categoryName));
    }
    @GetMapping("/Category")
    public ResponseEntity<List<CategoryStockDTO>> findPopularCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findPopularCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request));
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable String category_id ,@RequestBody @Valid CategoryRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(category_id,request));
    }

}
