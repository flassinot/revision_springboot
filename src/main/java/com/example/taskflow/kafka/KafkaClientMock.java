package com.example.taskflow.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!kafka")
@Component
public class KafkaClientMock implements KafkaClient {

    @Override
    public void sendMessage(String message) {
        System.out.println("send KafkaClientMock");
    }
}
