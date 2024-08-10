package com.example.softproject1.User;

import com.example.softproject1.User.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 학번으로 회원 정보 조회 (select * from member_table where student_id=?)
    Optional<MemberEntity> findByStudentId(String studentId);
}

