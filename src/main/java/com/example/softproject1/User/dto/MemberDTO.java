package com.example.softproject1.User.dto;

import com.example.softproject1.User.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String studentId;  // `Long` 타입으로 수정, 학번
    private String email;    // 이메일
    private String password; // 비밀번호
    private String name;     // 이름
    private String department; // 학부
    private String year;     // 학년
    private String phoneNumber; // 전화번호
    private String introduction; // 자기소개
    private String portfolio; // 포트폴리오

    // Entity를 DTO로 변환하는 메서드
    public static MemberDTO fromEntity(MemberEntity memberEntity) {
        if (memberEntity == null) {
            return null;
        }
        return new MemberDTO(
                memberEntity.getId(),
                memberEntity.getStudentId(),
                memberEntity.getEmail(),
                memberEntity.getPassword(),
                memberEntity.getName(),
                memberEntity.getDepartment(),
                memberEntity.getYear(),
                memberEntity.getPhoneNumber(),
                memberEntity.getIntroduction(),
                memberEntity.getPortfolio()
        );
    }
}
