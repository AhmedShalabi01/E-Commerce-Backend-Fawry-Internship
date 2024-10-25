package com.example.notificationapi.resources;


import com.example.notificationapi.dtos.EmailDto;

import com.example.notificationapi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
public class EmailResource {
    @Autowired
    private EmailService emailService;
    @PostMapping
    public void sendEmail(@RequestBody EmailDto emailDto){
        emailService.sendEmail(emailDto.getTo(), emailDto.getSubject(), emailDto.getText());
    }

}
