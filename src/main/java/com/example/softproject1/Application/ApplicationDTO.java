package com.example.softproject1.Application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter
@AllArgsConstructor
public class ApplicationDTO {
    private Long id; // 지원서 ID
    private Long articleId; // 게시글 ID
    private Long memberId; // 지원자 ID
    private LocalDate appliedAt; // 지원 시각
    private String name;
    private String department;
    private String year;
    private String status;


    public ApplicationDTO(Application application) {
        this.id = application.getId();
        this.name=application.getMember().getName();
        this.department=application.getMember().getDepartment();
        this.year=application.getMember().getYear();
        this.appliedAt = application.getAppliedAt();
        this.memberId = application.getMember().getId();
        this.articleId = application.getArticle().getId();
        this.status=application.getStatus();
    }
}
