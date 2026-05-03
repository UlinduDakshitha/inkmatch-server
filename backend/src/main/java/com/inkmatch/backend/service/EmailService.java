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

    public void sendWelcomeEmail(String toEmail, String username) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Welcome to InkMatch 🎨");

        message.setText(
                "Hi " + username + ",\n\n" +
                        "Welcome to InkMatch!\n\n" +
                        "You can now discover tattoo artists, explore designs, and book appointments easily.\n\n" +
                        "Enjoy your journey! 🚀\n\n" +
                        "- InkMatch Team"
        );

        mailSender.send(message);
    }

    public void sendBookingConfirmationEmail(
            String toEmail,
            String artistName,
            String date,
            String time,
            String location
    ) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Booking Confirmed 🎉");

        message.setText(
                "Your tattoo booking is confirmed!\n\n" +
                        "Artist: " + artistName + "\n" +
                        "Date: " + date + "\n" +
                        "Time: " + time + "\n" +
                        "Studio: " + location + "\n\n" +
                        "Thank you for choosing InkMatch!"
        );

        mailSender.send(message);
    }
}
