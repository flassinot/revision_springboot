package com.example.taskflow.controller;

import com.example.taskflow.dto.user.UserCreateDto;
import com.example.taskflow.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.taskflow.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Permet de jour des tests sur toute la stack Controller -> persistance
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("dev")
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService service;

    @Test
    void testCreateUser() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        UserCreateDto dto = new UserCreateDto("john", "john@test.com", "123");

        mvc.perform(post("/api/users")
                .contentType("application/json")
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        User john = service.findByUsername("john");
        Assertions.assertEquals("john@test.com", john.getEmail());
    }
}
