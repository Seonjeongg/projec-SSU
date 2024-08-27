package com.example.softproject1.project.ToDo;


import com.example.softproject1.Team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ToDoItemService toDoItemService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/{teamId}/todos")
    public ResponseEntity<List<ToDoItem>> getToDoItemsByTeamId(@PathVariable Long teamId) {
        // 팀을 확인할 수 있는 로직을 추가할 수 있음
        // 예를 들어, 팀이 존재하는지 확인
        if (!teamService.existsById(teamId)) {
            return ResponseEntity.notFound().build();
        }

        List<ToDoItem> toDoItems = toDoItemService.findByTeamId(teamId);
        return ResponseEntity.ok(toDoItems);
    }
}
