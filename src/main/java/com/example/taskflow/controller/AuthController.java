package com.example.taskflow.controller;

import com.example.taskflow.annotation.Loggable;
import com.example.taskflow.dto.user.UserCreateDto;
import com.example.taskflow.dto.user.UserRecord;
import com.example.taskflow.model.User;
import com.example.taskflow.security.JwtService;
import com.example.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    @Loggable
    public UserRecord register(@Valid @RequestBody UserCreateDto dto) {
        return userService.create(dto);
    }

    @PostMapping("/login")
    @Loggable
    public String login(@RequestParam String username, @RequestParam String password) {

        User user = userService.findByUsername(username);

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(username);
    }
}
