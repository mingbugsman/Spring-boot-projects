package com.ZZZZ.OrderService;


import com.ZZZZ.OrderService.DTO.request.OrderCreationRequest;
import com.ZZZZ.OrderService.DTO.response.OrderResponse;
import com.ZZZZ.OrderService.Enum.PaymentMethod;
import com.ZZZZ.OrderService.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
        for (int i = 0; i <5; i++) {
            OrderCreationRequest request = new OrderCreationRequest("c36a20b2-d975-4bcb-bda5-20d053e505af","00000243-0498-11f0-93d3-0242ac110002",12, "146 Nguyen Thi Rang", PaymentMethod.CREDIT_CARD);
            OrderResponse orderResponse = orderService.createOrder(request);
            System.out.println(orderResponse);
        }
    }
}
