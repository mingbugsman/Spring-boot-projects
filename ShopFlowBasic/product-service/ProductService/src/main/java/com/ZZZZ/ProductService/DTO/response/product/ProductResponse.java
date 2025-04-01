package com.ZZZZ.ProductService.DTO.response.product;


public record ProductResponse (
        String id,
        String name,
        String description,
        double price,
        int stock
) {}