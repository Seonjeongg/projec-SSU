package com.example.softproject1.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    // 기본적인 CRUD 메서드는 JpaRepository가 제공
}
