//package com.example.orderapi.services;
//
//import com.example.orderapi.dtos.EmailDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.net.http.HttpHeaders;
//
//@Service
//public class NotificationService {
//    @Autowired
//    private KafkaTemplate<String, EmailDto> kafkaTemplate;
//
//    public void send(EmailDto emailDto){
//        kafkaTemplate
//                .send("orderPlaced", emailDto);
//    }
//}
