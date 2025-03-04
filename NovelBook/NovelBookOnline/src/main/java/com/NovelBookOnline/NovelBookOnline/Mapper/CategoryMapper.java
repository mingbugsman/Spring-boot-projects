package com.NovelBookOnline.NovelBookOnline.Mapper;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Category.CategoryRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.Category.CategorySummaryResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "novels", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    Category toEntity(CategoryRequest request);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "novels", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    void updateEntity(@MappingTarget Category category, CategoryRequest request);

    CategorySummaryResponse toSummaryEntity(Category category);
}
