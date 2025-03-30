package com.ZZZZ.OrderService.kafka;


import com.ZZZZ.OrderService.Enum.OrderStatus;
import com.ZZZZ.OrderService.entity.Order;
import com.ZZZZ.OrderService.repository.OrderRepo;
import com.ZZZZ.commonDTO.Order.OrderCreatedEvent;
import com.ZZZZ.commonDTO.Order.OrderFailedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventConsumer {
    private final OrderRepo orderRepo;

    @KafkaListener(topics = "order-created", groupId = "order-service-group")
    @Transactional
    public void consumeOrderResponse(OrderCreatedEvent message) {
        log.info("Received order created: {}", message);
    }

    @KafkaListener(topics = "failed-order", groupId = "order-update-group")
    @Transactional
    public void consumeCancelOrder(OrderFailedEvent event) {
        System.out.println(event.toString());
        Order order = orderRepo.findByIdAndDeletedAtIsNull(event.getOrderId());
        order.setOrderStatus(OrderStatus.FAILED);
        orderRepo.save(order);
    }

}
