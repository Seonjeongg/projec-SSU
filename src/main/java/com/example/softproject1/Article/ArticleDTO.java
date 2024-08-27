package com.example.softproject1.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private Integer numberOfPeople;
    private LocalDate deadline;
    private String preferentialTreatment;
    private String field;
    private LocalDate createAt;
//
    private Long memberId;

    private String authorName;
    private String authorDepartment;

    public ArticleDTO(Long id,String title, String content, int numberOfPeople, LocalDate deadline,
                      String preferentialTreatment, String field, LocalDate createAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.numberOfPeople = numberOfPeople;
        this.deadline = deadline;
        this.preferentialTreatment = preferentialTreatment;
        this.field = field;
    }


    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.numberOfPeople = article.getNumberOfPeople();
        this.deadline = article.getDeadline();
        this.preferentialTreatment = article.getPreferentialTreatment();
        this.field = article.getField();
        this.createAt = article.getCreatedAt();
        this.memberId = article.getMember().getId();  // memberId 설정
        this.authorName = article.getMember().getName();  // 글쓴이 이름 설정
        this.authorDepartment = article.getMember().getDepartment();  // 글쓴이 학부 설정
    }
}
