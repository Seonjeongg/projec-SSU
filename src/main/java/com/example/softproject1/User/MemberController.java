package com.example.softproject1.User;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/save")
    public String save(@RequestBody MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "Member saved successfully";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginId", loginResult.getId());
            return "Login successful";
        } else {
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

    // 프로필 조회
    @GetMapping("/profile")
    public MemberDTO getProfile(HttpSession session) {
        Long myId = (Long) session.getAttribute("loginId");
        if (myId == null) {
            throw new IllegalStateException("User not logged in");
        }
        return memberService.findById(myId);
    }
}
