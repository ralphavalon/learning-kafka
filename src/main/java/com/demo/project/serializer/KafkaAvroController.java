package com.demo.project.serializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.listener.SampleObject;

@RestController
@RequestMapping("/api")
public class KafkaAvroController {

    @Autowired
    ProducerService producerService;

    @PostMapping(value = "/send")
    public String kafkaMessage(@RequestBody SampleObject message) {
        producerService.sendMessage(message);
        return "Success";
    }
}
