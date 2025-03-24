package com.ZZZZ.ProductService.kafka;


import com.ZZZZ.ProductService.entity.Product;
import com.ZZZZ.ProductService.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductEventConsumer {
    private final ProductRepo productRepo;
    private final RedisTemplate<String, Product> redisTemplate;


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
    public void consumeOrderEvent(String productId) {
        if (productId.equals("out of stock")) {
            log.warn("Received out-of-stock product. Ignoring...");
            return;  // Không throw exception để tránh Kafka retry
        }
        log.info("Receive message, product id : {}", productId);
        Product product = productRepo.getProduct(productId);
        if (product == null) {
            log.warn("Product {} not found. Ignoring event...", productId);
            return;
        }

        Product cachedProduct = (Product) redisTemplate.opsForValue().get("product:" + productId);
        if (cachedProduct != null) {
            product.setStock(cachedProduct.getStock());
        }

        productRepo.save(product);
        redisTemplate.opsForValue().set("product:" + productId, product);
    }

}
