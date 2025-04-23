package com.ZZZZ.OrderService.kafka;


import com.ZZZZ.OrderService.Enum.OrderStatus;
import com.ZZZZ.OrderService.entity.Order;
import com.ZZZZ.OrderService.repository.OrderRepo;
import com.ZZZZ.commonDTO.Order.OrderCreatedEvent;
import com.ZZZZ.commonDTO.Order.OrderFailedEvent;
import com.ZZZZ.commonDTO.Product.DeletedProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventConsumer {
    private final OrderRepo orderRepo;

    @KafkaListener(topics = "order-created", groupId = "order-service-group")
    @Transactional
    public void consumeOrderResponseEvent(OrderCreatedEvent message, Acknowledgment acknowledgment) {
        log.info("Received order created: {}", message);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "failed-order", groupId = "order-update-group")
    @Transactional
    public void consumeCancelOrderEvent(OrderFailedEvent event, Acknowledgment acknowledgment) {
        System.out.println(event.toString());
        Order order = orderRepo.findByIdAndDeletedAtIsNull(event.getOrderId());
        if (order == null) {
            log.info("Not found order");
            return;
        }
        order.setOrderStatus(OrderStatus.FAILED);
        orderRepo.save(order);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "deleted-product", groupId = "order-delete-service-group")
    @Transactional
    public void consumeDeleteProductEvent(DeletedProduct event, Acknowledgment acknowledgment) {
        log.info("Receive a message: {}", event);
        List<Order> orders = orderRepo.findByProductIdAndDeletedAtIsNull(event.getProductId());
        for (Order order : orders) {
            order.setDeletedAt(event.getDeletedAt());
        }
        orderRepo.saveAll(orders);
        acknowledgment.acknowledge();
    }

}
