package com.ZZZZ.OrderService.mapper;


import com.ZZZZ.OrderService.DTO.request.OrderCreationRequest;
import com.ZZZZ.OrderService.DTO.request.OrderUpdateRequest;
import com.ZZZZ.OrderService.DTO.response.OrderDetailResponse;
import com.ZZZZ.OrderService.DTO.response.OrderResponse;
import com.ZZZZ.OrderService.entity.Order;
import com.ZZZZ.commonDTO.Order.OrderCanceledEvent;
import com.ZZZZ.commonDTO.Order.OrderCreatedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public Order toOrder(OrderCreationRequest request) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setUserId(request.getUserId());
        order.setQuantity(request.getQuantity());
        order.setLocationShipping(request.getLocationShipping());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setUpdatedAt(null);
        return order;
    }

    public void updateOrder(Order order, OrderUpdateRequest request) {

        order.setPaymentMethod(request.getPaymentMethod() != null ? request.getPaymentMethod() : order.getPaymentMethod());
        order.setLocationShipping(request.getLocationShipping() != null ? request.getLocationShipping() : order.getLocationShipping());
        order.setQuantity(request.getQuantity());
    }

    public OrderDetailResponse toOrderDetailResponse(Order order) {
        return new OrderDetailResponse(
                order.getId(),
                order.getProductId(),
                order.getUserId(),
                order.getQuantity(),
                order.getLocationShipping(),
                order.getOrderStatus(),
                order.getPaymentMethod()
        );
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getQuantity(),
                order.getLocationShipping(),
                order.getOrderStatus(),
                order.getPaymentMethod()
        );
    }

    public OrderCreatedEvent toOrderCreatedEvent(Order order, String message) {
        if (order == null) return new OrderCreatedEvent(null, null, null, 0, null, message);
        return new OrderCreatedEvent(
                order.getId(),
                order.getProductId(),
                order.getUserId(),
                order.getQuantity(),
                order.getLocationShipping(),
                message
        );
    }

    public OrderCanceledEvent toOrderCanceledEvent(Order order) {
        return new OrderCanceledEvent(
                order.getId(),
                order.getUserId(),
                "Khong co nhu cau",
                LocalDateTime.now()
        );
    }

}
