package com.ZZZZ.EmailService.service;


import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;

public interface EmailService {
    public void sendWelcomeUserEmail(String subject, String template, Context context) throws MessagingException;
}
