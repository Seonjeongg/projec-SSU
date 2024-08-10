package com.example.softproject1.User.repository;

import com.example.softproject1.User.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String email);
    // 이메일로 회원 정보 조회 (select * from member_table where email=?)
//    Optional<MemberEntity> findByEmail(String email);
}
