package com.ZZZZ.EmailService.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MailConfigChecker {
    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @PostConstruct
    public void checkConfig() {
        System.out.println("Mail Host: " + mailHost);
        System.out.println("Mail Port: " + mailPort);
    }
}
