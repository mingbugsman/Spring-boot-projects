package com.ZZZZ.ProductService.config;



import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic productTopic() {
        return new NewTopic("product-events", 3, (short) 1);
    }

    @Bean
    public NewTopic deletedProduct() {
        return new NewTopic("deleted-product", 3, (short) 1);
    }

}
