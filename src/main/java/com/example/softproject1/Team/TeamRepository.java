package com.example.softproject1.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t JOIN t.members m WHERE m.id = :memberId")
    List<Team> findTeamsByMemberId(@Param("memberId") Long memberId);
}
