package com.example.taskflow.dto.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCreateDtoTest {

    @Test
    public void test() {
        new UserCreateDto().setEmail("aaaaaa{}");
    }
}