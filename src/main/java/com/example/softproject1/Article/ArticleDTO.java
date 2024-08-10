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
//    private Long id;
    private String title;
    private String content;
    private Integer numberOfPeople;
    private LocalDate deadline;
    private String preferentialTreatment;
    private String field;
//
    private Long memberId;
//    private String memberEmail;

    public ArticleDTO(String title, String content, int numberOfPeople, LocalDate deadline,
                      String preferentialTreatment, String field) {
//        this.id = id;
        this.title = title;
        this.content = content;
        this.numberOfPeople = numberOfPeople;
        this.deadline = deadline;
        this.preferentialTreatment = preferentialTreatment;
        this.field = field;
    }


    public ArticleDTO(Article article) {
        this.title=article.getTitle();
        this.content=article.getContent();
        this.numberOfPeople=article.getNumberOfPeople();
        this.deadline=article.getDeadline();
        this.preferentialTreatment=article.getPreferentialTreatment();
        this.field=article.getField();
    }
}
