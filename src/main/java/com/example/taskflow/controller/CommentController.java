package com.example.taskflow.controller;

import com.example.taskflow.dto.user.CommentCreateDto;
import com.example.taskflow.dto.user.CommentRecord;
import com.example.taskflow.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public CommentRecord create(@Valid @RequestBody CommentCreateDto dto) {
        return service.create(dto);
    }
}
