package com.example.taskflow.controller;

import com.example.taskflow.dto.user.TaskCreateDto;
import com.example.taskflow.dto.user.TaskRecord;
import com.example.taskflow.model.Project;
import com.example.taskflow.model.Task;
import com.example.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public TaskRecord create(@Valid @RequestBody TaskCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public TaskRecord findById(@PathVariable Long id) {
        Task t = service.findById(id);
        return new TaskRecord(t.getId(), t.getTitle(), t.getDescription(), t.getStatus(), t.getDueDate(),
                t.getProject().getId(), t.getAssignee().getId());
    }

    @GetMapping
    public List<TaskRecord> findAll() {
        return List.of();
    }
}
