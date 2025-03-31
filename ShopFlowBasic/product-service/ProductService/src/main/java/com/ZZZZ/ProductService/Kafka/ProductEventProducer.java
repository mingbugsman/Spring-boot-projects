package com.ZZZZ.ProductService.kafka;

import com.ZZZZ.commonDTO.Product.DeletedProduct;
import com.ZZZZ.commonDTO.Product.ProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductEventProducer {
    private final KafkaTemplate<String, ProductEvent> createdProductkafkaTemplate;
    private final KafkaTemplate<String, DeletedProduct> deletedProductKafkaTemplate;

    public void sendProductEvent(ProductEvent event) {createdProductkafkaTemplate.send("product-events", event);}

    public void sendDeletedProductEvent(DeletedProduct event) {deletedProductKafkaTemplate.send("deleted-product", event);}
}
