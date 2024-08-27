package com.example.softproject1.Team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamCreateRequest {
    private String name;
    private List<Long> memberIds;
    private Long creatorId;
}

