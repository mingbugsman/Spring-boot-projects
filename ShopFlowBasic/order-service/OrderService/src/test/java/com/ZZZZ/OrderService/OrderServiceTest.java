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
        OrderCreationRequest request = new OrderCreationRequest("93e9d4df-0285-4624-82b2-60ae3ea24b1e","00000243-0498-11f0-93d3-0242ac110002",12, "146 Nguyen Thi Rang", PaymentMethod.CREDIT_CARD);
        OrderResponse orderResponse = orderService.createOrder(request);
        System.out.println(orderResponse);
    }
}
