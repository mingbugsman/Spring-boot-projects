package com.ZZZZ.ProductService.DTO.response;

public record OrderResponse(
        String id,
        String userId,
        int quantity,
        String locationShipping,
        String orderStatus,
        String paymentMethod
) {}