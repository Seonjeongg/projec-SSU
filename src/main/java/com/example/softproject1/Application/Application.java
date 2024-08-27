package com.example.softproject1.Application;

import com.example.softproject1.Article.Article;
import com.example.softproject1.User.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article; //게시글

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member; //지원자

    @Column(nullable = false)
    private LocalDate appliedAt = LocalDate.now();//지원일자

    @Column(nullable = false)
    private String status;

}

