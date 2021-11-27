package com.marcelo.algafood.domain.service;

import com.marcelo.algafood.api.config.EmailConfig;
import com.marcelo.algafood.domain.model.EmailResponse;
import com.marcelo.algafood.domain.model.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class EmailSendService implements EmailService {

    @Autowired
    private EmailConfig emailConfig;


    public void sendMail(EmailResponse email) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        mailSender.setJavaMailProperties(javaMailProperties);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getEmailTo());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());
        mailMessage.setFrom("<your email>");

        mailSender.send(mailMessage);
    }

}