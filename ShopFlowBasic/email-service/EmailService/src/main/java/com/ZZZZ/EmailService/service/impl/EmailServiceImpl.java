package com.ZZZZ.EmailService.service.impl;

import com.ZZZZ.EmailService.base.util.EmailUtil;
import com.ZZZZ.EmailService.base.util.Generator;
import com.ZZZZ.EmailService.service.EmailService;
import com.ZZZZ.commonDTO.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.TimeUnit;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {
   JavaMailSender mailSender;
   TemplateEngine templateEngine;
   RedisTemplate<String, String> redisTemplate;

   @NonFinal
   @Value("${spring.mail.username}")
   private String sender;

    @Override
    public void sendWelcomeUserEmail(EmailRequest emailRequest) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        Context context = new Context();
        context.setVariable("senderEmail", sender);
        context.setVariable("replyTo", emailRequest.getTo());
        context.setVariable("name", emailRequest.getName());

        String htmlContent = templateEngine.process("EmailTemplate", context);

        helper.setTo(emailRequest.getTo());
        helper.setSubject("Welcome to shop service");
        helper.setText(htmlContent, true);
        mailSender.send(mimeMessage);
        System.out.println("Da send email");
    }

    @Override
    public void sendOTP(EmailRequest emailRequest) throws MessagingException {
        if (!EmailUtil.isValidEmail(emailRequest.getTo())) {
            log.info("Email is invalid");
            return;
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // generate otp
        String otp = Generator.generateOTPCode();
        redisTemplate.opsForValue().set("OTP:" + emailRequest.getTo(), otp, 10, TimeUnit.MINUTES);


        Context context = new Context();
        context.setVariable("OTPCode",otp);
        context.setVariable("username", emailRequest.getName());
        context.setVariable("senderEmail", sender);
        context.setVariable("replyTo", emailRequest.getTo());

        String htmlContent = templateEngine.process("EmailOTP", context);
        helper.setTo(emailRequest.getTo());
        helper.setSubject("Verify Account");
        helper.setText(htmlContent, true);
        mailSender.send(mimeMessage);

    }
}
