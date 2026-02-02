package com.example.taskflow;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Profile("kafka")
public class TestKafkaListener {

    public TestKafkaListener() {
        System.out.println("TestKafkaListener");
    }

    @KafkaListener(topics = "test-topic")
    public void listen(String message) {
        System.out.println(message);
    }
}
