package com.example.notificationapi.services;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
