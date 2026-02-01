package com.example.taskflow.dto.user;

import java.time.LocalDateTime;

public record CommentRecord(
        Long id,
        String content,
        Long authorId,
        Long taskId,
        LocalDateTime createdAt
) {}
