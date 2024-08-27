package com.example.softproject1.Team;

import com.example.softproject1.Application.ApplicationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class TeamDTO {
    private Long id;
    private String name;
    private List<ApplicationDTO> members;
}
