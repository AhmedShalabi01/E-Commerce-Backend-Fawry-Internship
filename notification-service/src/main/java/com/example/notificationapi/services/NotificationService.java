package com.example.notificationapi.services;

import com.example.notificationapi.dtos.EmailDto;
import com.example.notificationapi.dtos.EmailEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private EmailService emailService;
    @KafkaListener(topics = "orderPlaced", groupId = "notification-consumer-group")
    public void receiveOrderPlacedEvent(EmailDto emailDto) {
        emailService.sendEmail(emailDto.getTo(), emailDto.getSubject(), emailDto.getText());
    }

}
