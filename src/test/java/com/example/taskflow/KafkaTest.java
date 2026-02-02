package com.example.taskflow;

import com.example.taskflow.dto.user.UserCreateDto;
import com.example.taskflow.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = "test-topic")
@ActiveProfiles("kafka")
class KafkaTest {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    TestKafkaListener testListener;

    @Autowired
    UserService userService;

    @Test
    void testSend() throws InterruptedException {
        userService.create(new UserCreateDto("jack", "jack@test.com", "123"));

        Thread.sleep(10000);
    }
}
