package com.ZZZZ.OrderService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic orderCreatedTopic() {
        return new NewTopic("order-created", 3, (short) 2);
    }

    @Bean
    public NewTopic orderFailedTopic() {
        return new NewTopic("failed-order", 3, (short) 2);
    }

    @Bean
    public NewTopic orderCanceledTopic() {
        return new NewTopic("canceled-order", 3, (short) 2);
    }
}
