package com.example.taskflow.service;

import com.example.taskflow.dto.user.TaskCreateDto;
import com.example.taskflow.dto.user.TaskRecord;
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

    public TaskRecord create(TaskCreateDto dto) {
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

        return new TaskRecord(t.getId(), t.getTitle(), t.getDescription(), t.getStatus(), t.getDueDate(),
                t.getProject().getId(), t.getAssignee().getId());
    }

    public Task findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found: " + id));
    }
}
