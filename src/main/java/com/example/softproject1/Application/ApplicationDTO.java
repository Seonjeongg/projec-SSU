package com.example.softproject1.Application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter@Setter
@AllArgsConstructor
public class ApplicationDTO {
    private Long id; // 지원서 ID
    private Long articleId; // 게시글 ID
    private Long memberId; // 지원자 ID
    private LocalDateTime appliedAt; // 지원 시각

    public ApplicationDTO(Application application) {
        this.id = application.getId();
        this.articleId = application.getArticle().getId();
        this.memberId = application.getMember().getId();
        this.appliedAt = application.getAppliedAt();
    }
}
