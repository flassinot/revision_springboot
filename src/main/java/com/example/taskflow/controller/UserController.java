package com.example.taskflow.controller;

import com.example.taskflow.dto.user.UserCreateDto;
import com.example.taskflow.dto.user.UserDto;
import com.example.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody UserCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return service.toDto(service.findById(id));
    }
}
