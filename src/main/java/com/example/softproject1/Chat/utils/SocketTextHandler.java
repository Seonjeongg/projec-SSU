package com.example.softproject1.Chat.utils;

import com.example.softproject1.Application.Application;
import com.example.softproject1.Chat.Message;
import com.example.softproject1.Chat.MessageResponse;
import com.example.softproject1.Chat.models.Room;
import com.example.softproject1.Chat.MessageRepository;
import com.example.softproject1.Chat.repositories.RoomRepository;
import com.example.softproject1.User.MemberEntity;
import com.example.softproject1.User.MemberRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


@Component
public class SocketTextHandler extends TextWebSocketHandler {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        // 방 ID를 가져오기
        Long roomId = getRoomId(session);

        // 해당 방의 세션 리스트에 새 클라이언트 세션 추가
        roomRepository.room(roomId).sessions().add(session);

        System.out.println("새 클라이언트와 연결되었습니다.");

        // 해당 방의 이전 메시지를 로드하고 새로 연결된 클라이언트에게
        // 전송
        List<Message> previousMessages = messageRepository.findByRoomId(roomId);
        for (Message msg : previousMessages) {
            session.sendMessage(new TextMessage(msg.getTimestamp() + " " + msg.getUsername() + ": " + msg.getContent()));
        }
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // 클라이언트에서 보낸 메시지를 JSON으로 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message.getPayload());

        String messageType = jsonNode.get("type").asText();
        if ("messageData".equals(messageType)) {
            String username = jsonNode.get("username").asText();
            String content = jsonNode.get("content").asText();
            Long roomId = Long.parseLong(jsonNode.get("roomId").asText());
            LocalDateTime timestamp = LocalDateTime.parse(jsonNode.get("timestamp").asText(), DateTimeFormatter.ISO_DATE_TIME);

            // 메시지를 저장
            Message newMessage = new Message();
            newMessage.setRoomId(roomId);
            newMessage.setUsername(username);  // Username 설정
            newMessage.setContent(content);
            newMessage.setTimestamp(timestamp);
            messageRepository.save(newMessage);

            // 방의 모든 세션에 메시지 전송
            Room room = roomRepository.room(roomId);
            for (WebSocketSession connectedSession : room.sessions()) {
                connectedSession.sendMessage(new TextMessage(newMessage.getTimestamp() + " " + newMessage.getUsername() + ": " + newMessage.getContent()));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Long roomId = getRoomId(session);
        roomRepository.room(roomId).sessions().remove(session);

        System.out.println("특정 클라이언트와의 연결이 해제되었습니다.");
    }

    private Long getRoomId(WebSocketSession session) {
        String uri = Objects.requireNonNull(session.getUri()).toString();
        String[] parts = uri.split("/");
        String roomId = parts[parts.length - 1];
        return Long.parseLong(roomId);
    }

    private String getUsernameFromSession(WebSocketSession session) {
        return (String) session.getAttributes().get("username");
    }
}

