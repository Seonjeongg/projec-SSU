package com.example.softproject1.Article;

import com.example.softproject1.Application.Application;
import com.example.softproject1.Comment.Comment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.softproject1.User.entity.MemberEntity;

@Entity
@Getter @Setter
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column
    private LocalDate deadline;

    @Column(name = "preferential_treatement")
    private String preferentialTreatment;

    @Column
    private String field;

    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private MemberEntity member;

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Application> applications;

}


