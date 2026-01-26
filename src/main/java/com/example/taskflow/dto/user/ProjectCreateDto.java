package com.example.taskflow.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectCreateDto {

    @NotBlank
    private String name;

    private String description;

    // getters/setters
}
