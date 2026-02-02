package com.example.taskflow.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Profile("kafka")
@Component
public class KafkaClientImpl implements KafkaClient {

    KafkaTemplate<String, String> kafkaTemplate;

    public KafkaClientImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send("test-topic", message);
    }
}
