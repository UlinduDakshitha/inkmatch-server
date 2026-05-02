package com.inkmatch.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSupportEmail(String userEmail) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("support@inkmatch.lk"); // 👈 destination email
        message.setSubject("InkMatch Support Request");

        message.setText(
                "New support request from user email: " + userEmail
        );

        mailSender.send(message);
    }
}
