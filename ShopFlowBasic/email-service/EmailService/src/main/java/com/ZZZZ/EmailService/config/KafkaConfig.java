package com.ZZZZ.EmailService.config;


import com.ZZZZ.commonDTO.EmailRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, EmailRequest> kafkaTemplate(ProducerFactory<String, EmailRequest> producerFactory) {
        KafkaTemplate<String, EmailRequest> template = new KafkaTemplate<>(producerFactory);
        template.setMessageConverter(new StringJsonMessageConverter()); // Chặn metadata class
        return template;
    }

}
