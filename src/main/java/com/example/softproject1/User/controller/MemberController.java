package com.example.softproject1.User.controller;

import com.example.softproject1.User.dto.MemberDTO;
import com.example.softproject1.User.service.MemberService;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MemberDTO memberDTO) {
        if (memberDTO.getStudentId() == null) {
            return ResponseEntity.badRequest().body("studentId cannot be null");
        }
        memberService.save(memberDTO);
        return ResponseEntity.ok("Member saved successfully");
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginId", loginResult.getStudentId());
            return "Login successful";
        } else {
            // 로그인 실패
            return "Login failed";
        }
    }

    // 모든 회원 조회
    @GetMapping("/")
    public List<MemberDTO> findAll() {
        return memberService.findAll();
    }

    // 특정 회원 조회
    @GetMapping("/{id}")
    public MemberDTO findById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    // 회원 정보 수정 페이지
    @GetMapping("/update")
    public MemberDTO updateForm(HttpSession session) {
        Long myId = (Long) session.getAttribute("loginId");
        return memberService.findById(myId);
    }

    // 회원 정보 수정
    @PostMapping("/update")
    public String update(@RequestBody MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "Member updated successfully";
    }

    // 회원 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "Member deleted successfully";
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout successful";
    }

    // 이메일 중복 체크 기능 삭제
}
