package com.NovelBookOnline.NovelBookOnline.DTO.Request.Category;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequest {
    @NotNull(message = "Category name is required")
    String categoryName;

    @NotNull(message = "Category information is required")
    String categoryInformation;
}
