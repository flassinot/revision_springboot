package com.example.taskflow.service;

import com.example.taskflow.dto.user.TaskCreateDto;
import com.example.taskflow.dto.user.TaskDto;
import com.example.taskflow.exception.NotFoundException;
import com.example.taskflow.model.Project;
import com.example.taskflow.model.Task;
import com.example.taskflow.model.User;
import com.example.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repo;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskService(TaskRepository repo, ProjectService projectService, UserService userService) {
        this.repo = repo;
        this.projectService = projectService;
        this.userService = userService;
    }

    public TaskDto create(TaskCreateDto dto) {
        Project project = projectService.findById(dto.getProjectId());

        Task t = new Task();
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setDueDate(dto.getDueDate());
        t.setProject(project);

        if (dto.getAssigneeId() != null) {
            User assignee = userService.findById(dto.getAssigneeId());
            t.setAssignee(assignee);
        }

        repo.save(t);

        return toDto(t);
    }

    public Task findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found: " + id));
    }

    public TaskDto toDto(Task t) {
        TaskDto dto = new TaskDto();
        dto.setId(t.getId());
        dto.setTitle(t.getTitle());
        dto.setDescription(t.getDescription());
        dto.setStatus(t.getStatus());
        dto.setDueDate(t.getDueDate());
        dto.setProjectId(t.getProject().getId());
        dto.setAssigneeId(t.getAssignee() != null ? t.getAssignee().getId() : null);
        return dto;
    }
}
