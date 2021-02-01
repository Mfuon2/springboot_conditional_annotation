package com.example.conditional_app.controllers;

import com.example.conditional_app.services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
class KafkaController {

    private final ProducerService producer;

    @Autowired
    KafkaController(ProducerService producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publish")
    public void sendMessageToKafkaTopic() {
        String message = "This is the realest";
        this.producer.sendMessage(message);
    }
}
