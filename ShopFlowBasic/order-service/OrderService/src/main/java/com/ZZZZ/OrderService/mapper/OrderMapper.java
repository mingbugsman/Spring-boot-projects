package com.ZZZZ.OrderService.mapper;


import com.ZZZZ.OrderService.DTO.request.OrderCreationRequest;
import com.ZZZZ.OrderService.DTO.request.OrderUpdateRequest;
import com.ZZZZ.OrderService.DTO.response.OrderResponse;
import com.ZZZZ.OrderService.Enum.MessageStatus;
import com.ZZZZ.OrderService.entity.Order;
import com.ZZZZ.OrderService.kafka.OrderEvent;
import org.springframework.stereotype.Component;

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
        order.setOrderStatus(request.getOrderStatus() != null ? request.getOrderStatus() : order.getOrderStatus());
        order.setPaymentMethod(request.getPaymentMethod() != null ? request.getPaymentMethod() : order.getPaymentMethod());
        order.setLocationShipping(request.getLocationShipping() != null ? request.getLocationShipping() : order.getLocationShipping());
        order.setQuantity(request.getQuantity());
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProductId(),
                order.getUserId(),
                order.getQuantity(),
                order.getLocationShipping(),
                order.getOrderStatus(),
                order.getPaymentMethod()
        );
    }

    public OrderEvent toOrderEvent(Order order, String message) {
        if (order == null) return new OrderEvent(null, null, 0, null, message);
        return new OrderEvent(
                order.getProductId(),
                order.getUserId(),
                order.getQuantity(),
                order.getLocationShipping(),
                message
        );
    }

}
