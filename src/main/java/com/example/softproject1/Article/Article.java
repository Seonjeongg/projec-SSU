package com.example.softproject1.Article;

import com.example.softproject1.Application.Application;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.example.softproject1.User.MemberEntity;

@Entity
@Getter @Setter
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; //제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    @Column(name = "number_of_people")
    private Integer numberOfPeople; //모집인원

    @Column
    private LocalDate deadline;//마감기한

    @Column(name = "preferential_treatement")
    private String preferentialTreatment; //우대사항

    @Column
    private String field; //분야

    @ManyToOne
    @JoinColumn(name="member_id",nullable = false)
    private MemberEntity member;

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();  //게시날짜

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Application> applications;

}


