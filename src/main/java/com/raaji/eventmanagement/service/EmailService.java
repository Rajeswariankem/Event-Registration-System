package com.raaji.eventmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendCancellationEmail(
            String toEmail,
            String participantName,
            String eventName) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject(
                "Event Cancelled");

        message.setText(
                "Hello " + participantName +
                        ",\n\nThe event '" +
                        eventName +
                        "' has been cancelled." +
                        "\n\nSorry for the inconvenience.");

        mailSender.send(message);
    }

    public void sendRegistrationEmail(
            String toEmail,
            String participantName,
            String eventName) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject(
                "Event Registration Confirmation");

        message.setText(
                "Hello " + participantName +
                        ",\n\nYou have successfully registered for " +
                        eventName +
                        ".\n\nThank you!");

        mailSender.send(message);
    }
}