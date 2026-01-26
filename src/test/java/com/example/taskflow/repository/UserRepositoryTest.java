package com.example.taskflow.repository;

import com.example.taskflow.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFind() {
        User u = new User();
        u.setUsername("john");
        u.setEmail("john@test.com");
        u.setPassword("secret");

        userRepository.save(u);

        Assertions.assertTrue(userRepository.findByUsername("john").isPresent());
    }
}
