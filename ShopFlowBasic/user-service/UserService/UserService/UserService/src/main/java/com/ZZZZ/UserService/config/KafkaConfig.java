package com.ZZZZ.UserService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic userCreatedTopic() {
        return new NewTopic("user-created-event",3, (short)1);
    }

    @Bean
    public NewTopic userVerifyEmailTopic() {
        return new NewTopic("send-otp-event",3, (short)1);
    }
}
