package com.ZZZZ.UserService.kafka;


import com.ZZZZ.commonDTO.EmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventProducer {
    private final KafkaTemplate<String, EmailRequest> kafkaTemplate;

    public void sendUserCreatedEvent(EmailRequest request) {
        log.info("send message: {}", request);
        kafkaTemplate.send("user-created",request);
    }
}
