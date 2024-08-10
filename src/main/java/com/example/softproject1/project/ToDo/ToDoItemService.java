package com.example.softproject1.project.ToDo;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;

    public ToDoItemService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    /**
     * 모든 할 일 조회
     */
    public List<ToDoItem> findAll() {
        return toDoItemRepository.findAll();
    }

    /**
     * 단일 할 일 조회
     */
    public ToDoItem findById(Long id) {
        return toDoItemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 할 일을 찾을 수 없어요. id: [" + id + "]"));
    }

    /**
     * 할 일 저장
     */
    @Transactional
    public ToDoItem save(ToDoItemDto toDoItemDto) {
        // 엔티티 생성
        ToDoItem toDoItem = ToDoItem.createToDoItem(toDoItemDto.title(), toDoItemDto.description());

        // 엔티티 저장
        toDoItemRepository.save(toDoItem);

        return toDoItem;
    }

    /**
     * 할 일 수정
     */
    @Transactional
    public ToDoItem update(Long id, ToDoItemDto toDoItemDto) {
        // 엔티티 조회
        ToDoItem toDoItem = findById(id);

        // 엔티티 수정
        toDoItem.updateTitleAndDescription(toDoItemDto.title(), toDoItemDto.description());

        return toDoItem;
    }

    /**
     * 할 일 삭제
     */
    @Transactional
    public void deleteById(Long id) {
        // 엔티티 존재 여부 확인 및 삭제
        if (!toDoItemRepository.existsById(id)) {
            throw new NoSuchElementException("해당 할 일을 찾을 수 없어요. id: [" + id + "]");
        }
        toDoItemRepository.deleteById(id);
    }
}
