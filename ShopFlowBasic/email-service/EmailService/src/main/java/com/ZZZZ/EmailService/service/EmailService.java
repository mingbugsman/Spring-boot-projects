package com.ZZZZ.EmailService.service;


import com.ZZZZ.commonDTO.EmailRequest;
import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;

public interface EmailService {
     void sendWelcomeUserEmail(EmailRequest request) throws MessagingException;
     void sendOTP(EmailRequest request) throws MessagingException;
}
