package com.PhoneInventoryManager.PhoneInventory.Mapper;

import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category.CategoryDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Request.CategoryRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category.CategoryStockDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "phones", ignore = true)
    Category toCategory (CategoryRequest request);

    CategoryDTO toCategoryResponse(Category category);

    default CategoryStockDTO toCategoryStockDTO(Object[] object) {
        if (object == null || object.length < 2) {
            return null;
        }
        return new CategoryStockDTO(
                (String) object[0],
                ((Number) object[1]).longValue()
        );
    }

}
