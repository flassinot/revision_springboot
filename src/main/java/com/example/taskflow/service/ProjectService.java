package com.example.taskflow.service;

import com.example.taskflow.dto.user.ProjectCreateDto;
import com.example.taskflow.dto.user.ProjectRecord;
import com.example.taskflow.exception.NotFoundException;
import com.example.taskflow.model.Project;
import com.example.taskflow.model.User;
import com.example.taskflow.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository repo;
    private final UserService userService;

    public ProjectService(ProjectRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    public ProjectRecord create(ProjectCreateDto dto, Long ownerId) {
        User owner = userService.findById(ownerId);

        Project p = new Project();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setOwner(owner);
        p.setMembers(new HashSet<>());
        p.getMembers().add(owner);

        repo.save(p);

        return getProjectRecord(p);
    }

    public ProjectRecord getProjectRecord(Project p) {
        return new ProjectRecord(p.getId(), p.getName(), p.getDescription(), p.getOwner().getId(),
                p.getMembers().stream()
                        .map(User::getId)
                        .collect(Collectors.toSet()));
    }

    public Project findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found: " + id));
    }
}
