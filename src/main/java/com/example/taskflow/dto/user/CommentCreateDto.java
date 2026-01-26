package com.example.taskflow.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDto {

    @NotBlank
    private String content;

    @NotNull
    private Long taskId;

    @NotNull
    private Long authorId;

    // getters/setters
}
