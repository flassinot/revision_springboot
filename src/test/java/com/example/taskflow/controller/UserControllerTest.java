package com.example.taskflow.controller;

import com.example.taskflow.dto.user.UserCreateDto;
import com.example.taskflow.security.JwtFilter;
import com.example.taskflow.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Permet de jour des tests sur toute la stack Controller -> persistance
 */
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("dev")
class UserControllerTest {

    @MockitoBean
    private JwtFilter jwtFilter;

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UserService service;

    @Test
    void testCreateUser() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        UserCreateDto dto = new UserCreateDto("john", "john@test.com", "123");

        mvc.perform(post("/api/users")
                .contentType("application/json")
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}
