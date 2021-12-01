package com.marcelo.algafood.domain.service;

import com.marcelo.algafood.domain.exception.EmailException;
import com.marcelo.algafood.domain.model.EmailProperties;
import com.marcelo.algafood.domain.model.interfaces.SendMailServiceInterface;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class SendEmailService implements SendMailServiceInterface {

    @Autowired
    public JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Override
    public void sendMessage(Message message) {

        System.out.println("SendEmailService : Email sera enviado para : " + message.getRecipients());

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(message.getRecipients().toArray(new String[0]));
            helper.setSubject(message.getSubject());
            helper.setText(message.getBody(), true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar email : " + e);
        }
    }
}
