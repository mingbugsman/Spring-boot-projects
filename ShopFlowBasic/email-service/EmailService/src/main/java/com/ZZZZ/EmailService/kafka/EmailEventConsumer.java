package com.ZZZZ.EmailService.kafka;

import com.ZZZZ.EmailService.service.EmailService;
import com.ZZZZ.commonDTO.EmailRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailEventConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "send-otp-event", groupId = "email-service-group")
    public String verifyEmailConsume(EmailRequest emailRequest, Acknowledgment acknowledgment) {
        log.info("Received message to verify email: {}", emailRequest);
        try {
            emailService.sendOTP(emailRequest);
            acknowledgment.acknowledge();
            return "Email sent successfully";

        } catch (MessagingException e) {
            return "Error sending email: " + e.getMessage();
        }
    }

    @KafkaListener(topics = "user-created-event", groupId = "email-service-group")
    public String emailServiceListen(EmailRequest emailRequest, Acknowledgment acknowledgment) {
        log.info("Received message: {}", emailRequest);
        try {
            emailService.sendWelcomeUserEmail(emailRequest);
            acknowledgment.acknowledge();
            return "Email sent successfully";
        } catch (MessagingException e) {
            return "Error sending email: " + e.getMessage();
        }
    }

}
