package com.ZZZZ.OrderService.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderCreatedEvent(String message) {
        kafkaTemplate.send("order-created", message);
    }
}
