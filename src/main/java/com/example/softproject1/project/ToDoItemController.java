package com.example.softproject1.project;

import com.example.softproject1.project.ToDoItem;
import com.example.softproject1.project.ToDoItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoItemController {

    private final ToDoItemService toDoItemService;

    public ToDoItemController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    /**
     * 모든 ToDoItem 조회
     */
    @GetMapping
    public List<ToDoItem> getAllToDoItems() {
        return toDoItemService.findAll();
    }
}