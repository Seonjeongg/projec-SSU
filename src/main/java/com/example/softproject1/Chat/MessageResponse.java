package com.example.softproject1.Chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class MessageResponse {
    // Getters and setters

    private Long id;

    private Long roomId;

    private String content;

    private String username;  // 사용자 이름
    private LocalDateTime timestamp;

    public MessageResponse(Long id, Long roomId, String content, LocalDateTime timestamp, String username) {
        this.id = id;
        this.roomId = roomId;
        this.content = content;
        this.timestamp = timestamp;
        this.username = username;
    }
}
