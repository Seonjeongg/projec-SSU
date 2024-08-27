package com.example.softproject1.User;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return new ResponseEntity<>("Member saved successfully", HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody MemberDTO memberDTO) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // 로그인 성공 시 사용자 ID를 Long 타입으로 반환
            return new ResponseEntity<>(loginResult.getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 모든 회원 조회
    @GetMapping("/login")
    public ResponseEntity<List<MemberDTO>> findAll() {
        List<MemberDTO> members = memberService.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // 특정 회원 조회
    @GetMapping("/login/{id}")
    public ResponseEntity<MemberDTO> findById(@PathVariable Long id) {
        MemberDTO member = memberService.findById(id);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 회원 정보 수정 페이지
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        // ID를 기반으로 회원 정보 수정
        memberDTO.setId(id);  // 클라이언트로부터 받은 DTO에 ID 설정
        memberService.update(memberDTO);
        return new ResponseEntity<>("Member updated successfully", HttpStatus.OK);
    }

    // 회원 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return new ResponseEntity<>("Member deleted successfully", HttpStatus.NO_CONTENT);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }

    // 프로필 조회
    @GetMapping("/profile/{id}")
    public ResponseEntity<MemberDTO> getProfile(@PathVariable Long id) {
        MemberDTO member = memberService.findById(id);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

}
