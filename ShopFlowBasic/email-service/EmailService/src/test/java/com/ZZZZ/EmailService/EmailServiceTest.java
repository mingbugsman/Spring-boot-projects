package com.ZZZZ.EmailService;


import com.ZZZZ.EmailService.service.EmailService;
import com.ZZZZ.commonDTO.EmailRequest;
import jakarta.mail.MessagingException;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;


    @NonFinal
    @Value("${spring.mail.username}")
    private String sender;

    @Test
    public void sendEmail() {
        EmailRequest emailRequest = new EmailRequest("ming18380@gmail.com", "Nguyen Hoang Thuong Anh");
        try {
            emailService.sendWelcomeUserEmail(emailRequest);
            System.out.println("email sent successfully");
        } catch (MessagingException e) {
            System.out.println( "Error sending email: " + e.getMessage());
        }
    }

    @Test
    public void sendOTP() {
        EmailRequest emailRequest = new EmailRequest( "1250080116@sv.hcmunre.edu.vn", "Nguyen Hoang Thuong Anh");
        try {
            emailService.sendOTP(emailRequest);
            System.out.println("email sent successfully");
        } catch (MessagingException e) {
            System.out.println("Error sending email:" + e.getMessage());
        }
    }

}
