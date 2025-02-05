package com.PhoneInventoryManager.PhoneInventory.Service;


import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category.CategoryDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Request.CategoryRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category.CategoryStockDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Category;
import com.PhoneInventoryManager.PhoneInventory.Mapper.CategoryMapper;
import com.PhoneInventoryManager.PhoneInventory.Repository.PhoneCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryService {
    CategoryMapper mapper;
    PhoneCategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(mapper::toCategoryResponse).toList();
    }
    public CategoryDTO getCategory(String id) throws Exception {
        var response = categoryRepository.findById(id).orElseThrow(() -> new Exception("not found category"));
        return mapper.toCategoryResponse(response);
    }
    public List<CategoryStockDTO> getCategoriesWithPhoneCount() {
        return categoryRepository.getCategoriesWithPhoneCount().stream().map(mapper::toCategoryStockDTO).toList();
    }
    public CategoryStockDTO getCategoryWithPhoneCount(String categoryName) throws Exception {
        Object[] result =(Object[])  categoryRepository.getCategoryWithPhoneCount(categoryName);
        if (result == null) {
            throw new Exception("Not found specification category with name " + categoryName);
        }
        return mapper.toCategoryStockDTO(result);
    }

    public List<CategoryStockDTO> findPopularCategories() {
        return categoryRepository.findPopularCategories().stream().map(mapper::toCategoryStockDTO).toList();
    }

    public List<CategoryStockDTO> getStockByCategory() {
        return categoryRepository.getStockByCategory().stream().map(mapper::toCategoryStockDTO).toList();
    }

    public CategoryDTO createCategory( CategoryRequest request) {
        log.info(request.toString());
        Category category = mapper.toCategory(request);
        category = categoryRepository.save(category);
        return mapper.toCategoryResponse(category);
    }

    public CategoryDTO updateCategory(String category_id, CategoryRequest request) throws Exception {
        var category = categoryRepository.findById(category_id).orElseThrow(()  -> new Exception("not found category id"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        categoryRepository.save(category);
        return mapper.toCategoryResponse(category);
    }

}
