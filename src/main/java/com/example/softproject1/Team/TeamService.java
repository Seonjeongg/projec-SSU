package com.example.softproject1.Team;

import com.example.softproject1.Application.Application;
import com.example.softproject1.Application.ApplicationDTO;
import com.example.softproject1.Application.ApplicationRepository;
import com.example.softproject1.User.MemberEntity;
import com.example.softproject1.User.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    // 팀 생성 메소드
    public Team createTeam(String teamName, List<Long> memberIds) {
        Team team = new Team();
        team.setName(teamName);

        // 팀에 멤버 추가
        List<MemberEntity> members = memberRepository.findAllById(memberIds);
        team.setMembers(members);

        // 팀 저장
        return teamRepository.save(team);
    }

    // 특정 게시물에 대한 지원자 목록 가져오기
    public List<ApplicationDTO> getApplicantsByArticle(Long articleId) {
        List<Application> applications = applicationRepository.findByArticleId(articleId);
        return applications.stream()
                .map(ApplicationDTO::new)
                .collect(Collectors.toList());
    }

    public boolean existsById(Long teamId) {
        return teamRepository.existsById(teamId);
    }


    // 사용자가 속한 팀 목록을 반환하는 메서드
    public List<Team> getTeamsByUserId(Long userId) {
        // 사용자가 속한 팀을 조회
        return teamRepository.findTeamsByMemberId(userId);
    }


    public void addMemberToTeam(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        team.getMembers().add(member);
        teamRepository.save(team);
    }
}
