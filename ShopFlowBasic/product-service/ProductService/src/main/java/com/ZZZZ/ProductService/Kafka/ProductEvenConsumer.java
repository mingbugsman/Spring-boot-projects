package com.ZZZZ.ProductService.Kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductEvenConsumer {

    @KafkaListener(topics = "product-events", groupId = "product-group")
    public void consumeProductEvent(ProductEvent event) {
        log.info("Received product: {}", event);

        switch (event.getEventType()) {
            case CREATED:
                log.info("New product added: {}", event.getMessage());
                break;

            case OUT_OF_STOCK:
                log.warn("WARNING: Product out of stock: {}", event.getMessage());
                break;

            case UPDATE_STOCK:
                log.warn("UPDATE: Product have been updated stock: {}", event.getMessage());
                break;
            default:
                log.warn("Unknown event type received: {}", event.getEventType());
        }
    }
}
