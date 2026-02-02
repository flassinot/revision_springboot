package com.example.taskflow.controller;

import com.example.taskflow.dto.user.ProjectCreateDto;
import com.example.taskflow.dto.user.ProjectRecord;
import com.example.taskflow.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ProjectRecord create(
            @Valid @RequestBody ProjectCreateDto dto,
            @RequestParam Long ownerId
    ) {
        return service.create(dto, ownerId);
    }

    @GetMapping("/{id}")
    public ProjectRecord findById(@PathVariable Long id) {
        return service.getProjectRecord(service.findById(id));
    }
}
