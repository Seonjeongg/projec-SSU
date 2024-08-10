package com.example.softproject1.User;

import com.example.softproject1.User.MemberDTO;
import com.example.softproject1.User.MemberEntity;
import com.example.softproject1.User.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.fromDTOWithoutId(memberDTO);
        memberRepository.save(memberEntity);
    }

    // 로그인
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByStudentId(memberDTO.getStudentId());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                return MemberDTO.fromEntity(memberEntity);
            }
        }
        return null;
    }

    // 모든 회원 조회
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.fromEntity(memberEntity));
        }
        return memberDTOList;
    }

    // 특정 회원 조회
    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        return optionalMemberEntity.map(MemberDTO::fromEntity).orElse(null);
    }

    // 회원 정보 수정
    public void update(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberDTO.getId());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            memberEntity.setEmail(memberDTO.getEmail());
            memberEntity.setPassword(memberDTO.getPassword());
            memberEntity.setName(memberDTO.getName());
            memberEntity.setPhoneNumber(memberDTO.getPhoneNumber());
            memberEntity.setPortfolio(memberDTO.getPortfolio());
            memberEntity.setYear(memberDTO.getYear());
            memberEntity.setIntroduction(memberDTO.getIntroduction());
            memberRepository.save(memberEntity);
        } else {
            throw new IllegalArgumentException("Member with ID " + memberDTO.getId() + " not found");
        }
    }

    // 회원 삭제
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    // 학번 중복 체크
    public String studentIdCheck(String studentId) {
        Optional<MemberEntity> byStudentId = memberRepository.findByStudentId(studentId);
        return byStudentId.isPresent() ? "no" : "ok";
    }
}
