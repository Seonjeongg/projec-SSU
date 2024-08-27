//package com.example.softproject1.Chat;
//
//import com.example.softproject1.Chat.MessageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.stream.Collectors;
//@CrossOrigin("*")
//@RestController
//public class ChatController {
//
//    private final MessageRepository messageRepository;
//
//    @Autowired
//    public ChatController(MessageRepository messageRepository) {
//        this.messageRepository = messageRepository;
//    }
//
//    @GetMapping("/messages/room/{roomId}")
//    public List<MessageResponse> getMessagesByRoomId(@PathVariable Long roomId) {
//        List<Message> messages = messageRepository.findByRoomId(roomId);
//
//        return messages.stream()
//                .map(message -> new MessageResponse(
//                        message.getId(),
//                        message.getRoomId(),
//                        message.getContent(),   // content 인자를 세 번째로 이동
//                        message.getTimestamp(), // timestamp 인자를 네 번째로 이동
//                        message.getMemberId()) // username 인자를 마지막으로 이동
//                .collect(Collectors.toList());
//    }
//}
