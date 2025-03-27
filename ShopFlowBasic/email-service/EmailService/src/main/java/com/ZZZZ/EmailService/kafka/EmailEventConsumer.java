package com.ZZZZ.EmailService.kafka;

import com.ZZZZ.EmailService.service.EmailService;
import com.ZZZZ.commonDTO.EmailRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailEventConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "user-created", groupId = "email-service-group")
    public String emailServiceListen(EmailRequest emailRequest) {
        log.info("Received message: {}", emailRequest);
        Context context = new Context();
        try {
            emailService.sendWelcomeUserEmail("Welcome to shop service", "emailTemplate", context);
            return "Email sent successfully";
        } catch (MessagingException e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
