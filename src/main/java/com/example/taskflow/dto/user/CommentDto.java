package com.example.taskflow.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {

    private Long id;
    private String content;
    private Long authorId;
    private Long taskId;
    private LocalDateTime createdAt;

    // getters/setters
}
