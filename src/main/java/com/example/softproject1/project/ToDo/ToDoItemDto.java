package com.example.softproject1.project.ToDo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
@Getter
@Setter
public class ToDoItemDto {
    @NotBlank
    private String title;
    private String description;
    private Long teamId; // 팀 ID 추가

    // Getters and Setters
}