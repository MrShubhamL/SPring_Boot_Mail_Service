package com.smtp.mailserver.service;

import jakarta.mail.MessagingException;

import java.io.File;

public interface EmailService {
    void sendEmail(String to, String subject, String message);
    void sendEmails(String[] to, String subject, String message);
    boolean sendEmailWithHtml(String to, String subject, String htmlContent) throws MessagingException;
    void sendEmailWithFile(String to, String subject, String message, File file) throws MessagingException;
}
