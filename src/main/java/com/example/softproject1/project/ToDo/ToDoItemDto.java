package com.example.softproject1.project.ToDo;

import org.springframework.lang.NonNull;

public record ToDoItemDto(@NonNull String title, @NonNull String description) {
}

