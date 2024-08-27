package com.example.softproject1.project.ToDo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    List<ToDoItem> findByTeamId(Long teamId);
    // 기본적인 CRUD 메서드는 JpaRepository가 제공
}
