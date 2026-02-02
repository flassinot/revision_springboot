package com.example.taskflow.dto.user;

import com.example.taskflow.model.TaskStatus;

import java.time.LocalDate;

public record TaskRecord (
    Long id,
    String title,
    String description,
    TaskStatus status,
    LocalDate dueDate,
    Long projectId,
    Long assigneeId
) {}
