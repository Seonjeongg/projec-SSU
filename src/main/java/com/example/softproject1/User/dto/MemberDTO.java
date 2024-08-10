package com.example.softproject1.User.dto;

import com.example.softproject1.User.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long studentId; // 학번(아이디)
    private String password; // 비밀번호
    private String name; // 이름
    private String department; // 학부
    private String year; // 학년
    private String phoneNumber; // 전화번호
    private String email; // 이메일
    private String introduction; // 자기소개
    private String portfolio; // 포트폴리오

    public static MemberDTO fromEntity(MemberEntity memberEntity) {
        if (memberEntity == null) {
            return null;
        }
        return new MemberDTO(
                memberEntity.getStudentId(), // 학번(아이디)
                memberEntity.getPassword(), // 비밀번호
                memberEntity.getName(), // 이름
                memberEntity.getDepartment(), // 학부
                memberEntity.getYear(), // 학년
                memberEntity.getPhoneNumber(), // 전화번호
                memberEntity.getEmail(), // 이메일
                memberEntity.getIntroduction(), // 자기소개
                memberEntity.getPortfolio() // 포트폴리오
        );
    }
}
