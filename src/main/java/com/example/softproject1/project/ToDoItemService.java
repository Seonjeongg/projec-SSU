package com.example.softproject1.project;

import com.example.softproject1.project.ToDoItem;
import com.example.softproject1.project.ToDoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;

    public ToDoItemService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    /*
     * 모든 할 일 조회
     */
    public List<ToDoItem> findAll() {
        return toDoItemRepository.findAll();
    }
}

