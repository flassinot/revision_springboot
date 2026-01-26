package com.example.taskflow.controller;

import com.example.taskflow.dto.user.TaskCreateDto;
import com.example.taskflow.dto.user.TaskDto;
import com.example.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public TaskDto create(@Valid @RequestBody TaskCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public TaskDto findById(@PathVariable Long id) {
        return service.toDto(service.findById(id));
    }
}
