package com.ZZZZ.OrderService.kafka;


import com.ZZZZ.OrderService.repository.OrderRepo;
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
    public void consumeOrderResponse(String message) {
        log.info("Received order created: {}", message);

    }
}
