package com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryStockDTO {
    String categoryName;
    Long totalStock;
}
