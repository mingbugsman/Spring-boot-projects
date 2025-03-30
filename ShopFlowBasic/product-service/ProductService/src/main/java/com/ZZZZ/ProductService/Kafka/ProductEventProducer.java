package com.ZZZZ.ProductService.kafka;

import com.ZZZZ.commonDTO.Product.ProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductEventProducer {
    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

    public void sendProductEvent(ProductEvent event) {
        kafkaTemplate.send("product-events", event);
    }
}
