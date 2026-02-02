package com.example.taskflow.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDto (
        @NotBlank String username,
        @Email @NotBlank String email,
        @NotBlank String password) {}
