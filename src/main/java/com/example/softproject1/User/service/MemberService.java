package com.example.softproject1.User.service;

import com.example.softproject1.User.dto.MemberDTO;
import com.example.softproject1.User.entity.MemberEntity;
import com.example.softproject1.User.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // Convert DTO to entity without ID
        MemberEntity memberEntity = MemberEntity.fromDTOWithoutId(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByEmail(memberDTO.getEmail());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                // Convert entity to DTO and return
                return MemberDTO.fromEntity(memberEntity);
            }
        }
        // Return null if login fails
        return null;
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.fromEntity(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        return optionalMemberEntity.map(MemberDTO::fromEntity).orElse(null);
    }

    public MemberDTO updateForm(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        return optionalMemberEntity.map(MemberDTO::fromEntity).orElse(null);
    }

    public void update(MemberDTO memberDTO) {
        // Update with ID
        memberRepository.save(MemberEntity.fromDTO(memberDTO));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
