package com.example.softproject1.Comment;

import com.example.softproject1.Article.Article;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    @JsonBackReference
    private Article article;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
