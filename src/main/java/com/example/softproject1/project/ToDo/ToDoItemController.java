package com.example.softproject1.project.ToDo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/teams/{teamId}/todos")
public class ToDoItemController {

    private final ToDoItemService toDoItemService;

    public ToDoItemController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    /**
     * 특정 팀의 모든 ToDoItem 조회
     */
    @GetMapping
    public List<ToDoItem> getAllToDoItemsByTeamId(@PathVariable Long teamId) {
        return toDoItemService.findByTeamId(teamId);
    }

    /**
     * 특정 팀의 ToDoItem 등록
     */
    @PostMapping
    public Long createToDoItem(@PathVariable Long teamId, @RequestBody @Valid ToDoItemDto toDoItemDto) {
        toDoItemDto.setTeamId(teamId); // 팀 ID 설정
        ToDoItem toDoItem = toDoItemService.save(toDoItemDto);
        return toDoItem.getId();
    }

    /**
     * 특정 ID를 이용한 ToDoItem 조회
     */
    @GetMapping("/{id}")
    public ToDoItem getToDoItemById(@PathVariable Long id) {
        return toDoItemService.findById(id);
    }

    /**
     * 할 일 수정
     */
    @PatchMapping("/{id}")
    public Long updateToDoItem(@PathVariable Long id, @RequestBody @Valid ToDoItemDto toDoItemDto) {
        ToDoItem toDoItem = toDoItemService.update(id, toDoItemDto);
        return toDoItem.getId();
    }

    /**
     * 할 일 삭제
     */
    @DeleteMapping("/{id}")
    public void deleteToDoItem(@PathVariable Long id) {
        toDoItemService.deleteById(id);
    }
}


