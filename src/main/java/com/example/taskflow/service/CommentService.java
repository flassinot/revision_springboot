package com.example.taskflow.service;

import com.example.taskflow.dto.user.CommentCreateDto;
import com.example.taskflow.dto.user.CommentDto;
import com.example.taskflow.dto.user.CommentRecord;
import com.example.taskflow.model.Comment;
import com.example.taskflow.model.Task;
import com.example.taskflow.model.User;
import com.example.taskflow.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository repo;
    private final TaskService taskService;
    private final UserService userService;

    public CommentService(CommentRepository repo, TaskService taskService, UserService userService) {
        this.repo = repo;
        this.taskService = taskService;
        this.userService = userService;
    }

    public CommentRecord create(CommentCreateDto dto) {
        Task task = taskService.findById(dto.getTaskId());
        User author = userService.findById(dto.getAuthorId());

        Comment c = new Comment();
        c.setContent(dto.getContent());
        c.setTask(task);
        c.setAuthor(author);

        repo.save(c);

        return new CommentRecord(
                c.getId(),
                c.getContent(),
                c.getAuthor().getId(),
                c.getTask().getId(),
                c.getCreatedAt()
        );
    }

    private CommentDto toDto(Comment c) {
        CommentDto dto = new CommentDto();
        dto.setId(c.getId());
        dto.setContent(c.getContent());
        dto.setAuthorId(c.getAuthor().getId());
        dto.setTaskId(c.getTask().getId());
        dto.setCreatedAt(c.getCreatedAt());
        return dto;
    }
}
