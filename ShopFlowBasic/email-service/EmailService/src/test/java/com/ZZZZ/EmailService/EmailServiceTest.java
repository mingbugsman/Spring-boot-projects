package com.ZZZZ.EmailService;


import com.ZZZZ.EmailService.DTO.EmailRequest;
import com.ZZZZ.EmailService.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.context.Context;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;


    @NonFinal
    @Value("${spring.mail.username}")
    private String sender;

    @Test
    public void sendEmail() {
        EmailRequest emailRequest = new EmailRequest("ming18380@gmail.com","Welcome user", "Welcome to shop service", "Nguyen Hoang Thuong Anh");
        Context context = new Context();
        context.setVariable("senderEmail", sender);
        context.setVariable("replyTo", emailRequest.getTo());
        context.setVariable("name", emailRequest.getName());
        context.setVariable("message", emailRequest.getMessage());
        context.setVariable("subject", emailRequest.getSubject());

        try {
            emailService.sendWelcomeUserEmail( emailRequest.getSubject(), "emailTemplate", context);
            System.out.println("email sent successfully");
        } catch (MessagingException e) {
            System.out.println( "Error sending email: " + e.getMessage());
        }
    }

}
