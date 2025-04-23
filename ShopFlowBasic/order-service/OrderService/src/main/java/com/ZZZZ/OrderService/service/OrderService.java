package com.ZZZZ.OrderService.service;

import com.ZZZZ.OrderService.DTO.request.OrderCreationRequest;
import com.ZZZZ.OrderService.DTO.request.OrderUpdateRequest;
import com.ZZZZ.OrderService.DTO.response.OrderDetailResponse;
import com.ZZZZ.OrderService.DTO.response.OrderResponse;
import org.springframework.data.domain.Page;



public interface OrderService {
    public OrderDetailResponse createOrder(OrderCreationRequest request);
    public OrderDetailResponse updateInformationOrder(String id, OrderUpdateRequest request);
    public String cancelOrder(String id);
    public void deleteOrder(String id);
    // get
    public Page<OrderDetailResponse> getAllOrder(int page, int size, String sortBy);
    public Page<OrderResponse> getOrdersByProductId(String productId, int page, int size, String sortBy);
    public Page<OrderDetailResponse> getOrdersByUserId(String userId, int page, int size, String sortBy);

}
