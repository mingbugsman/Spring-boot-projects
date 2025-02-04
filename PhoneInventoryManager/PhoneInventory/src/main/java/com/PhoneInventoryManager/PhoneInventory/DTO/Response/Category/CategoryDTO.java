package com.PhoneInventoryManager.PhoneInventory.DTO.Response.Category;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO {
    String id;
    String name;
    String description;

}
