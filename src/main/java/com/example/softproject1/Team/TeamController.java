package com.example.softproject1.Team;

import com.example.softproject1.Application.ApplicationDTO;
import com.example.softproject1.Application.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
@CrossOrigin("*")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private ApplicationService applicationService;

    // 팀 생성 요청을 처리하는 메소드
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamCreateRequest request) {
        // 상태가 확정된 지원자만 필터링
        List<Long> confirmedMemberIds = applicationService.getConfirmedMemberIds();

        // 작성자 ID 가져오기
        Long creatorId = request.getCreatorId();

        // 팀 생성
        Team team = teamService.createTeam(request.getName(), confirmedMemberIds);

        // 작성자를 팀의 멤버로 추가
        teamService.addMemberToTeam(team.getId(), creatorId);

        // DTO로 변환
        TeamDTO teamDTO = new TeamDTO(
                team.getId(),
                team.getName(),
                team.getMembers().stream()
                        .map(member -> new ApplicationDTO(
                                null, // 지원서 ID는 필요 없으니 null
                                null, // 게시글 ID는 필요 없으니 null
                                member.getId(), // 지원자 ID
                                null, // 지원 시각은 필요 없으니 null
                                member.getName(),
                                member.getDepartment(),
                                member.getYear(),
                                null
                        ))
                        .collect(Collectors.toList())
        );
        return ResponseEntity.ok(teamDTO);
    }

    // 특정 게시물에 대한 지원자 목록을 조회하는 메소드
    @GetMapping("/article/{articleId}/applicants")
    public ResponseEntity<List<ApplicationDTO>> getApplicantsByArticle(@PathVariable Long articleId) {
        List<ApplicationDTO> applicants = teamService.getApplicantsByArticle(articleId);
        return ResponseEntity.ok(applicants);
    }

    // 사용자가 속한 팀 목록을 반환하는 메소드
    @GetMapping("/my/{user_id}")
    public ResponseEntity<List<TeamDTO>> getMyTeams(@PathVariable("user_id") Long userId) {
        // 사용자가 속한 팀 목록을 조회
        List<Team> teams = teamService.getTeamsByUserId(userId);

        // 팀을 DTO로 변환
        List<TeamDTO> teamDTOs = teams.stream()
                .map(team -> new TeamDTO(
                        team.getId(),
                        team.getName(),
                        team.getMembers().stream()
                                .map(member -> new ApplicationDTO(
                                        null, // 지원서 ID는 필요 없으니 null
                                        null, // 게시글 ID는 필요 없으니 null
                                        member.getId(), // 지원자 ID
                                        null, // 지원 시각은 필요 없으니 null
                                        member.getName(), // 지원자 이름
                                        member.getDepartment(), // 부서
                                        member.getYear(), // 학년
                                        null // 추가 정보는 필요 없으니 null
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(teamDTOs);
    }

}