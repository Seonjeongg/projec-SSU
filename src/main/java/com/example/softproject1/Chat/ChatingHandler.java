//package com.example.softproject1.Chat;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Slf4j
//public class ChatingHandler extends TextWebSocketHandler {
//
//    @Autowired
//    public ChatService cService;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    // 채팅방 목록 <방 번호, ArrayList<session> >이 들어간다.
//    private Map<Integer, ArrayList<WebSocketSession>> RoomList = new ConcurrentHashMap<>();
//
//    // session, 방 번호가 들어간다.
//    private Map<WebSocketSession,Integer> sessionList = new ConcurrentHashMap<>();
//
//    private static int i;
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        i++;
//        log.info("연결 성공! 현재인원 ={}",i);
//        ChatMember cm = (ChatMember)session.getAttributes().get("chatMember");
//        int maxPersonnel = (Integer)session.getAttributes().get("maxPersonnel");
//        cService.insertApply(cm, maxPersonnel);
//
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        i--;
//        log.info("연결 종료! 현재인원 = {}",i);
//        if(sessionList.get(session) != null) {
//            RoomList.get(sessionList.get(session)).remove(session);
//            sessionList.remove(session);
//        }
//        log.info("session={}",session.getAttributes());
//        ChatMember cm = (ChatMember)session.getAttributes().get("chatMember");
//        cService.outCaht(cm);
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//
//        //전달 받은 메세지
//        String msg = message.getPayload();
//
//        // Json 객체 -> java객체
//        ChatMessage chatMessage = objectMapper.readValue(msg, ChatMessage.class);
//        log.info("chatMessage = {}", chatMessage);
//        // 받은 메세지에 담긴 roomId로 해당 채팅방을 찾아온다.
//        ChatRoom chatRoom = cService.selectChatRoom(chatMessage.getRoomId());
//        log.info("chatRoom = {}",chatRoom);
//
//        TextMessage textMessage = new TextMessage(chatMessage.getMemberNo() + "," + chatMessage.getMemberName() + "," + chatMessage.getMessage() + "," + chatMessage.getMessageType());
//
//        if(chatRoom == null && chatMessage.getMessage().equals("ENTER-CHAT")) {
//            chatRoom = new ChatRoom(Integer.parseInt(chatMessage.getRoomId()),chatMessage.getMemberNo());
//            int result = cService.createChat(chatRoom);
//
//            if(result>0) {
//                // 채팅 세션 목록에 채팅방이 존재하지 않고, 처음 들어왔고, DB에서의 채팅방이 있을 때
//                ArrayList<WebSocketSession> sessionTwo = new ArrayList<>();
//                sessionTwo.add(session);
//                sessionList.put(session, chatRoom.getRoomId());
//
//                // 해당 채팅방에 참여한 세션들 추가
//                RoomList.put(chatRoom.getRoomId(), sessionTwo);
//
//                log.info("채팅방 생성");
//                for(WebSocketSession sess : RoomList.get(chatRoom.getRoomId())) {
//                    sess.sendMessage(textMessage);
//                }
//            }
//
//            // 채팅 세션 목록에 채팅방이 존재하지 않고, 처음 들어왔고, DB에서의 채팅방이 있을 때
//        }else if(RoomList.get(chatRoom.getRoomId()) == null && chatMessage.getMessage().equals("ENTER-CHAT") && chatRoom != null) {
//
//            ArrayList<WebSocketSession> sessionTwo = new ArrayList<>();
//            sessionTwo.add(session);
//            sessionList.put(session, chatRoom.getRoomId());
//
//            // 해당 채팅방에 참여한 세션들 추가
//            RoomList.put(chatRoom.getRoomId(), sessionTwo);
//
//            for(WebSocketSession sess : RoomList.get(chatRoom.getRoomId())) {
//                sess.sendMessage(textMessage);
//            }
//
//            log.info("채팅방 입장");
//            // 채팅방이 존재할때
//        }else if(RoomList.get(chatRoom.getRoomId()) != null && chatMessage.getMessage().equals("ENTER-CHAT") && chatRoom != null){
//            // RoomList에서 해당 방번호를 가진 방이 있는지 확인.
//            RoomList.get(chatRoom.getRoomId()).add(session);
//            // sessionList에 추가
//            sessionList.put(session, chatRoom.getRoomId());
//
//
//            for(WebSocketSession sess : RoomList.get(chatRoom.getRoomId())) {
//                sess.sendMessage(textMessage);
//            }
//
//        }
//        // 메세지 입력 시
//        else if(RoomList.get(chatRoom.getRoomId())!= null && !chatMessage.getMessage().equals("ENTER-CHAT") && !chatMessage.getMessage().equals("END-CHAT")&& chatRoom != null && !chatMessage.getMessage().equals("EXILE-CHAT")) {
//
//
//            //현재 session 수
//            int sessionCount = 0;
//
//            // 해탕 채팅방의 모든 session들에게 메세지를 뿌린다.
//            for(WebSocketSession sess : RoomList.get(chatRoom.getRoomId())) {
//                sess.sendMessage(textMessage);
//                sessionCount++;
//            }
//
//
//
//            // 메세지를 읽었는지 안읽었는지 확인을 위해
//            chatMessage.setSessionCount(sessionCount);
//
//            // DB에 메세지 저장
//            int result =cService.insertMessage(chatMessage);
//
//
//            log.info("chatMessage = {}",chatMessage);
//
//            if(result == 1) {
//                log.info("메세지 DB에 저장 성공");
//            }else {
//                log.info("메세지 전송 실패");
//            }
//
//            // 채팅방 나가기
//        }else if(RoomList.get(chatRoom.getRoomId())!= null && chatMessage.getMessage().equals("END-CHAT") && chatRoom != null) {
//            ChatMember cm = new ChatMember(Integer.parseInt(chatMessage.getRoomId()),chatMessage.getMemberNo(),chatMessage.getRole(),"","");
//            cService.outCaht(cm);
//            log.info("sessionList = {}",sessionList);
//            log.info("RoomList = {}",RoomList);
//            RoomList.get(sessionList.get(session)).remove(session);
//            sessionList.remove(session);
//
//            for(WebSocketSession sess : RoomList.get(chatRoom.getRoomId())) {
//                sess.sendMessage(textMessage);
//            }
//            // 추방하기
//        }else if(RoomList.get(chatRoom.getRoomId())!= null && chatMessage.getMessage().equals("EXILE-CHAT") && chatRoom != null) {
//            textMessage = new TextMessage(chatMessage.getMemberNo()+",,"+chatMessage.getMessage()+","+chatMessage.getMessageType());
//            for(WebSocketSession sess : RoomList.get(chatRoom.getRoomId())) {
//                sess.sendMessage(textMessage);
//            }
//        }
//    }
//
//
//
//
//
//
//
//}