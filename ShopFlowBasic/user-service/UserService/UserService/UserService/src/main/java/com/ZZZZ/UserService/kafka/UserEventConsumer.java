package com.ZZZZ.UserService.kafka;

import com.ZZZZ.commonDTO.Order.OrderFailedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserEventConsumer {

    @KafkaListener(topics = "failed-order", groupId = "user-order-service")
    public void consumeFailedOrder(OrderFailedEvent event, Acknowledgment acknowledgment) {
        log.info("order id : {} is failed, reason is {}", event.getOrderId(), event.getReason());
        acknowledgment.acknowledge();
    }
}
