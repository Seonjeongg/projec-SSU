package com.example.softproject1.Chat;

import com.example.softproject1.Application.Application;
import com.example.softproject1.User.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId;

    private String username;  // 사용자 이름

    private String content;

    private LocalDateTime timestamp;

    public Message() {}

    public Message(Application application, String content) {
        this.roomId = application.getMember().getId();
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.username = application.getMember().getName();
    }

}

