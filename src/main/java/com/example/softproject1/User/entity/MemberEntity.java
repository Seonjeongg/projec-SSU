package com.example.softproject1.User.entity;

import com.example.softproject1.Application.Application;
import com.example.softproject1.Article.Article;
import com.example.softproject1.User.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "User")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long studentId; // 학번(아이디)

    @Column
    private String email; // 이메일

    @Column
    private String password; // 비밀번호

    @Column
    private String name; // 이름

    @Column
    private String department; // 학부

    @Column
    private String year; // 학년

    @Column
    private String phoneNumber; // 전화번호

    @Column
    private String introduction; // 자기소개

    @Column
    private String portfolio; // 포트폴리오

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Application> applications;


    // Convert DTO to Entity
    public static MemberEntity fromDTO(MemberDTO memberDTO) {
        if (memberDTO == null) {
            return null;
        }
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setStudentId(memberDTO.getStudentId()); // 학번(아이디)
        memberEntity.setEmail(memberDTO.getEmail()); // 이메일
        memberEntity.setPassword(memberDTO.getPassword()); // 비밀번호
        memberEntity.setName(memberDTO.getName()); // 이름
        memberEntity.setDepartment(memberDTO.getDepartment()); // 학부
        memberEntity.setYear(memberDTO.getYear()); // 학년
        memberEntity.setPhoneNumber(memberDTO.getPhoneNumber()); // 전화번호
        memberEntity.setIntroduction(memberDTO.getIntroduction()); // 자기소개
        memberEntity.setPortfolio(memberDTO.getPortfolio()); // 포트폴리오
        return memberEntity;
    }

    // Convert DTO to Entity without ID (for creation purposes)
    public static MemberEntity fromDTOWithoutId(MemberDTO memberDTO) {
        if (memberDTO == null) {
            return null;
        }
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setStudentId(memberDTO.getStudentId());
        memberEntity.setEmail(memberDTO.getEmail()); // 이메일
        memberEntity.setPassword(memberDTO.getPassword()); // 비밀번호
        memberEntity.setName(memberDTO.getName()); // 이름
        memberEntity.setDepartment(memberDTO.getDepartment()); // 학부
        memberEntity.setYear(memberDTO.getYear()); // 학년
        memberEntity.setPhoneNumber(memberDTO.getPhoneNumber()); // 전화번호
        memberEntity.setIntroduction(memberDTO.getIntroduction()); // 자기소개
        memberEntity.setPortfolio(memberDTO.getPortfolio()); // 포트폴리오
        return memberEntity;
    }
}
