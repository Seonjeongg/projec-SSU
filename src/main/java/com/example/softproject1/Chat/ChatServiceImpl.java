//package com.example.softproject1.Chat;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class ChatServiceImpl implements ChatService{
//
//    @Autowired
//    private ChatDao cDao;
//    @Autowired
//    private SqlSessionTemplate sqlSession;
//
//
//    @Override
//    public ChatRoom selectChatRoom(String roomId) {
//        return cDao.selectChatRoom(sqlSession, roomId);
//    }
//
//    @Override
//    public int insertMessage(ChatMessage chatMessage) {
//        return cDao.insertMessage(sqlSession,chatMessage);
//    }
//
//    @Override
//    public ArrayList<ChatMessage> messageList(int roomId) {
//        return cDao.messageList(sqlSession,roomId);
//    }
//
//    @Override
//    public int createChat(ChatRoom room) {
//        return cDao.createChat(sqlSession,room);
//    }
//
//    @Override
//    public ChatRoom searchChatRoom(ChatRoom room) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public List<ChatRoom> chatRoomList(String userEmail) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public int selectUnReadCount(ChatMessage message) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int updateCount(ChatMessage message) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int outCaht(ChatMember cm) {
//        return cDao.outChat(sqlSession, cm);
//    }
//    @Override
//    public int getApply(ChatMember cm) {
//        return cDao.getApply(sqlSession, cm);
//    }
//    @Override
//    public int insertApply(ChatMember cm, int maxPersonnel) {
//        int apply = getApply(cm);
//        int result = 0;
//        if(apply<maxPersonnel) {
//            result = cDao.insertApply(sqlSession, cm);
//        }
//        return result;
//    }
//
//    @Override
//    public ArrayList<ChatMember> chatMemberList(int projectNo) {
//        return cDao.chatMemberList(sqlSession, projectNo);
//    }
//
//    @Override
//    public Project getProject(int projectNo) {
//        return cDao.getProject(sqlSession, projectNo);
//    }
//
//    @Override
//    public ArrayList<Integer> getFixApply(ChatMember cm) {
//        return cDao.getFixApply(sqlSession, cm);
//    }
//
//}