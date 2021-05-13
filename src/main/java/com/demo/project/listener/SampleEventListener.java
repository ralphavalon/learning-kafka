package com.demo.project.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

@Component
public class SampleEventListener implements ConsumerSeekAware {

    @KafkaListener(topics = "Samples")
    public void process(String message) {
        System.out.println("Message found.");
        System.out.println(message);
    }
    
}
