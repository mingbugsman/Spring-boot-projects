package com.ZZZZ.ProductService.DTO.request;


import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {
    String name;
    String description;
    @PositiveOrZero(message = "price is not negative number")
    double price;
    @PositiveOrZero(message = "stock is not negative number")
    int stock;
}
