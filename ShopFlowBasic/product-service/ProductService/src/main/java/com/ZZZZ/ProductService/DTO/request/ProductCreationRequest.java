package com.ZZZZ.ProductService.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {

    @NotNull(message = "product name is required")
    String name;
    String description;

    @NotNull(message = "price is required")
    @Positive
    double price;

    @NotNull(message = "stock is required")
    @PositiveOrZero(message = "stock cannot below zero")
    int stock;
}
