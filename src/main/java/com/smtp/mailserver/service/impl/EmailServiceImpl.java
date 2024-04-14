package com.smtp.mailserver.service.impl;

import com.smtp.mailserver.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("mr.shubhamlohar952@gmail.com");
        javaMailSender.send(simpleMailMessage);
        logger.info("Mail sent successfully.");
    }

    @Override
    public void sendEmails(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("mr.shubhamlohar952@gmail.com");
        javaMailSender.send(simpleMailMessage);
        logger.info("Mail sent successfully.");
    }

    @Override
    public boolean sendEmailWithHtml(String to, String subject, String htmlContent) {
        MimeMessage simMimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(simMimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("mr.shubhamlohar952@gmail.com");
            helper.setText(htmlContent, true);
            javaMailSender.send(simMimeMessage);
            logger.info("Email has been sent.");
            return true;
        } catch (MessagingException e){
            return false;
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(message);
        helper.setFrom("mr.shubhamlohar952@gmail.com");
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), file);
        javaMailSender.send(mimeMessage);
        logger.info("Mail has been sent successfully.");
    }
}
