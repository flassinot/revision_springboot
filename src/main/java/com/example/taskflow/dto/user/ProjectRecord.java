package com.example.taskflow.dto.user;

import java.util.Set;

public record ProjectRecord (
    Long id,
    String name,
    String description,
    Long ownerId,
    Set<Long> memberIds
) {}
