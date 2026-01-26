package com.example.taskflow.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskCreateDto {

    @NotBlank
    private String title;

    private String description;

    private LocalDate dueDate;

    @NotNull
    private Long projectId;

    private Long assigneeId;

    // getters/setters
}
