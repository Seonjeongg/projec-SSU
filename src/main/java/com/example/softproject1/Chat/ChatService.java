/*
package com.example.softproject1.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatRoom startChat(ChatRequest chatRequest) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setArticleId(chatRequest.getArticleId());
        chatRoom.setMemberId(chatRequest.getMemberId());
        chatRoom = chatRoomRepository.save(chatRoom);

        return chatRoom;
    }
}*/
