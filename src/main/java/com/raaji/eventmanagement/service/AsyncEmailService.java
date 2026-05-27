package com.raaji.eventmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendConfirmationEmail(String to, String eventName) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject("Event Registration Successful");
        mail.setText("You registered for " + eventName);

        mailSender.send(mail);
    }
}