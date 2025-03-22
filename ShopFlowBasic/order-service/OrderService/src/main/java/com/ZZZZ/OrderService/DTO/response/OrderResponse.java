package com.ZZZZ.OrderService.DTO.response;

import com.ZZZZ.OrderService.Enum.OrderStatus;
import com.ZZZZ.OrderService.Enum.PaymentMethod;

public record OrderResponse(
        String id,
        String productId,
        String userId,
        int quantity,
        String locationShipping,
        OrderStatus orderStatus,
        PaymentMethod paymentMethod
) {}