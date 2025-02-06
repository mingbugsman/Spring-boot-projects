package com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone;


import com.PhoneInventoryManager.PhoneInventory.DTO.Response.PhoneImage.PhoneImageDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Specification.SpecificationDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneDTO {
    String id;
    String model;
    String brand;
    BigDecimal price;
    Integer stockQuantity;

    @Builder.Default
    int soldQuantity = 0;
    SpecificationDTO specification;

    List<PhoneImageDTO> images;
    LocalDateTime createdAt;
    String categoryName;
}
