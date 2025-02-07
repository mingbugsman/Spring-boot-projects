package com.PhoneInventoryManager.PhoneInventory.Mapper;

import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category.CategoryDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Request.CategoryRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category.CategoryStockDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "phones", ignore = true)
    Category toCategory (CategoryRequest request);


    CategoryDTO toCategoryResponse(Category category);


    default CategoryStockDTO toCategoryStockDTO(Object[] data) {
        CategoryStockDTO categoryStockDTO = new CategoryStockDTO();
        categoryStockDTO.setCategoryName((String)data[0]);
        categoryStockDTO.setTotalStock((Long)data[1]);
        return categoryStockDTO;
    }


}
