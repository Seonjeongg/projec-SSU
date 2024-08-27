package com.example.softproject1.project.ToDo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "todo_item")
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title; //제목

    @Column(name = "description", length = 500)
    private String description; //투두 내용

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; //생성날짜

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; //수정날짜

    @Column
    private Long teamId; //팀 id

    protected ToDoItem() {}

    private ToDoItem(String title,String description){
        this.title = title;
        this.description = description;
    }


    public static ToDoItem createToDoItem(String title, String description) {
        return new ToDoItem(title, description);
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // 비즈니스 메서드
    public void updateTitleAndDescription(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
