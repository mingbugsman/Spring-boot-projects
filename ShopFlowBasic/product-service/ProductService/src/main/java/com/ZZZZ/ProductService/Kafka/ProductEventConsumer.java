package com.ZZZZ.ProductService.kafka;


import com.ZZZZ.ProductService.entity.Product;
import com.ZZZZ.ProductService.repository.ProductRepo;
import com.ZZZZ.commonDTO.Order.OrderCreatedEvent;
import com.ZZZZ.commonDTO.Order.OrderFailedEvent;
import com.ZZZZ.commonDTO.Product.ProductEvent;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductEventConsumer {
    private final ProductRepo productRepo;
    private final RedisTemplate<String, Product> redisTemplate;
    private final EntityManager entityManager;
    private final KafkaTemplate<String, OrderFailedEvent> failedOrderEvent;

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

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void consumeOrderEvent(OrderCreatedEvent event) {
       log.info("received a message:{}",event.toString());
        Product product = productRepo.getProduct(event.getProductId());
        if (product == null) {
            log.warn("Product {} not found. Ignoring event...", event.getProductId());
            return;
        }
        if (product.getStock() <= 0 || product.getStock() < event.getQuantity()) {
            failedOrderEvent.send("failed-order", new OrderFailedEvent(event.getOrderId(), "Product is out of stock or insufficient stock"));
            return;
        }

        productRepo.decreaseStock(event.getProductId(), event.getQuantity());
        entityManager.clear();
        Product updatedProduct = productRepo.getProduct(event.getProductId());
        redisTemplate.opsForValue().set("product:" + updatedProduct.getId(), updatedProduct);
    }
}
