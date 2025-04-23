package com.ZZZZ.ProductService.DTO.response.product;

import com.ZZZZ.ProductService.DTO.response.OrderResponse;

import java.util.List;

public record ProductWithOrdersResponse(
        String productId,
        String productName,
        List<OrderResponse> orderResponseList
)
{ }
