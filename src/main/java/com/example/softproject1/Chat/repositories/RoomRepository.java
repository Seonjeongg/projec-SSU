package com.example.softproject1.Chat.repositories;

import com.example.softproject1.Chat.models.Room;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class RoomRepository {
    private final Map<Long, Room> rooms;

    // 생성자에서 rooms 필드를 초기화
    public RoomRepository() {
        this.rooms = Stream.generate(() -> Room.create("채팅방"))
                .limit(100)
                .collect(Collectors.toMap(
                        Room::id,
                        room -> room
                ));
    }

    // ID로 Room 객체를 반환하는 메서드
    public Room room(Long id) {
        return rooms.get(id);
    }
}

