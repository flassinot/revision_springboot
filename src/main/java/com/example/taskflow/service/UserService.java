package com.example.taskflow.service;

import com.example.taskflow.annotation.Loggable;
import com.example.taskflow.dto.user.UserCreateDto;
import com.example.taskflow.dto.user.UserRecord;
import com.example.taskflow.exception.NotFoundException;
import com.example.taskflow.kafka.KafkaClient;
import com.example.taskflow.model.User;
import com.example.taskflow.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository repo;
    private final KafkaClient kafkaClient;

    public UserService(UserRepository repo, KafkaClient kafkaClient) {
        this.repo = repo;
        this.kafkaClient = kafkaClient;
    }

    @Loggable
    public UserRecord create(UserCreateDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password()); // hash plus tard

        repo.save(user);
        kafkaClient.sendMessage("User créé : " + user.getUsername());

        return new UserRecord(user.getId(), user.getUsername(), user.getEmail());
    }

    public User findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found: " + username));
    }
}
