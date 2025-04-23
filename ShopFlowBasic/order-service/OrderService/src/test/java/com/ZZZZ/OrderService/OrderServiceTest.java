package com.ZZZZ.OrderService;


import com.ZZZZ.OrderService.DTO.request.OrderCreationRequest;
import com.ZZZZ.OrderService.DTO.response.OrderDetailResponse;
import com.ZZZZ.OrderService.DTO.response.OrderResponse;
import com.ZZZZ.OrderService.Enum.PaymentMethod;
import com.ZZZZ.OrderService.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
        for (int i = 0; i <5; i++) {
            OrderCreationRequest request = new OrderCreationRequest("c36a20b2-d975-4bcb-bda5-20d053e505af","00000243-0498-11f0-93d3-0242ac110002",12, "146 Nguyen Thi Rang", PaymentMethod.CREDIT_CARD);
            OrderDetailResponse orderResponse = orderService.createOrder(request);
            System.out.println(orderResponse);
        }
    }

    @Test
    public void deleteOrder() {
        String id = "77e3c13a-abcd-4d60-9f22-8a9d70f549f6";
        orderService.deleteOrder(id);
    }

    @Test
    public void getAllOrder() {
        int page = 0;
        int size = 10;
        String sortBy = "createdAt";
        Page<OrderDetailResponse> orders =  orderService.getAllOrder(page, size, sortBy);
        for (OrderDetailResponse orderResponse : orders) {
            System.out.println(orderResponse);
        }
    }

    @Test
    public void getOrdersByProductId() {
        int page = 0;
        int size = 10;
        String sortBy = "createdAt";
        String productId = "93e9d4df-0285-4624-82b2-60ae3ea24b1e";
        Page<OrderResponse> orders =  orderService.getOrdersByProductId(productId,page, size, sortBy);
        for (OrderResponse orderResponse : orders) {
            System.out.println(orderResponse);
        }
    }

    @Test
    public void getOrdersByUserId() {
        int page = 0;
        int size = 10;
        String sortBy = "createdAt";
        String userId = "00000243-0498-11f0-93d3-0242ac110002";
        Page<OrderDetailResponse> orders =  orderService.getOrdersByUserId(userId,page, size, sortBy);
        for (OrderDetailResponse orderResponse : orders) {
            System.out.println(orderResponse);
        }
    }

}
