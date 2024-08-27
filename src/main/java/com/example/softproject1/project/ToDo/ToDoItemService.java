package com.example.softproject1.project.ToDo;

import com.example.softproject1.Application.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ToDoItemService {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    //팀 id 찾기
    public List<ToDoItem> findByTeamId(Long teamId) {
        return toDoItemRepository.findByTeamId(teamId);
    }

    //투두 리스트 저장
    public ToDoItem save(ToDoItemDto toDoItemDto) {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setTitle(toDoItemDto.getTitle());
        toDoItem.setDescription(toDoItemDto.getDescription());
        toDoItem.setTeamId(toDoItemDto.getTeamId()); // 팀 ID 설정
        return toDoItemRepository.save(toDoItem);
    }

    //투두 리스트 수젇
    public ToDoItem update(Long id, ToDoItemDto toDoItemDto) {
        ToDoItem toDoItem = toDoItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ToDoItem not found"));
        toDoItem.setTitle(toDoItemDto.getTitle());
        toDoItem.setDescription(toDoItemDto.getDescription());
        toDoItem.setTeamId(toDoItemDto.getTeamId()); // 팀 ID 업데이트
        return toDoItemRepository.save(toDoItem);
    }

    //투두 리스트 삭제
    public void deleteById(Long id) {
        toDoItemRepository.deleteById(id);
    }

    //해당 투두 찾기
    public ToDoItem findById(Long id) {
        return toDoItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ToDoItem not found with id " + id));
    }
}
