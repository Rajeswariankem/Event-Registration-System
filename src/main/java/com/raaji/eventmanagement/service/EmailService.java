package com.raaji.eventmanagement.service;

import com.raaji.eventmanagement.entity.Event;
import com.raaji.eventmanagement.entity.Participant;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    // 1. ADDED THIS OVERLOADED METHOD TO FIX THE EVENT SERVICE CALL
    public void sendCancellationEmail(Event event) {
        // If your event has a list of participants, notify them safely
        if (event.getParticipants() != null) {
            for (Participant participant : event.getParticipants()) {
                try {
                    sendCancellationEmail(
                            participant.getEmail(),
                            participant.getName(),
                            event.getEventName()
                    );
                } catch (Exception e) {
                    System.err.println("Failed to send cancellation to " + participant.getEmail());
                }
            }
        }
    }

    // Your original method remains safe and intact
    public void sendCancellationEmail(
            String toEmail,
            String participantName,
            String eventName) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Event Cancelled");
        message.setText(
                "Hello " + participantName + ",\n\n" +
                        "The event '" + eventName + "' has been cancelled.\n\n" +
                        "Sorry for the inconvenience.");

        mailSender.send(message);
    }

    public void sendRegistrationEmail(
            String toEmail,
            String participantName,
            String eventName) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Event Registration Confirmation");
        message.setText(
                "Hello " + participantName + ",\n\n" +
                        "You have successfully registered for " + eventName + ".\n\n" +
                        "Thank you!");

        mailSender.send(message);
    }
}