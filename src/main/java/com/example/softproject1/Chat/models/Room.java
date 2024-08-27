package com.example.softproject1.Chat.models;

import com.example.softproject1.Chat.utils.RoomIdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Room {
    private Long id;

    private String name;

    private final Set<WebSocketSession> sessions = new HashSet<>();

    public static Room create(String name) {
        Room room = new Room();
        room.id = RoomIdGenerator.createId();
        room.name = name;
        return room;
    }

    public Long id() {
        return id;
    }

    public Set<WebSocketSession> sessions() {
        return sessions;
    }
}