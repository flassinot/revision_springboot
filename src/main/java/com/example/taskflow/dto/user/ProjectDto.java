package com.example.taskflow.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProjectDto {

    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private Set<Long> memberIds;

    // getters/setters
}
