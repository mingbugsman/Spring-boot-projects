package com.PhoneInventoryManager.PhoneInventory.DTO.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneRequest {
    @NotBlank
    String model;

    @NotBlank
    String brand;

    @Positive
    BigDecimal price;

    @PositiveOrZero
    Integer stockQuantity;

    String imagePath;

    @NotNull
    String categoryId;

    Integer soldQuantity;

    SpecificationRequest specification;

}
