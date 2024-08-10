package com.example.softproject1.Application;


import com.example.softproject1.User.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class ApplicationDetailDTO {

    private Long id;
    private Long articleId;
    private MemberDTO member; // MemberDTO를 포함

    private LocalDateTime appliedAt;

    public ApplicationDetailDTO(Application application) {
        if (application.getMember() == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        this.id = application.getId();
        this.articleId = application.getArticle().getId();
        this.member = new MemberDTO(); // 기본 생성자 사용
        this.member.setStudentId(application.getMember().getStudentId());
//        this.member.setPassword(application.getMember().getPassword());
        this.member.setEmail(application.getMember().getEmail());
        this.member.setName(application.getMember().getName());
        this.member.setDepartment(application.getMember().getDepartment());
        this.member.setYear(application.getMember().getYear());
        this.member.setPhoneNumber(application.getMember().getPhoneNumber());
        this.member.setIntroduction(application.getMember().getIntroduction());
        this.member.setPortfolio(application.getMember().getPortfolio());
        this.appliedAt = application.getAppliedAt();
    }


}
