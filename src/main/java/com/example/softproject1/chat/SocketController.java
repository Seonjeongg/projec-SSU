package com.example.softproject1.chat;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {

    // /receive를 메시지를 받을 endpoint로 설정합니다.
    @PostMapping("/receive")
    public SocketVO handleMessage(@RequestBody SocketVO socketVO) {
        // vo에서 getter로 userName을 가져옵니다.
        String userName = socketVO.getUserName();
        // vo에서 setter로 content를 가져옵니다.
        String content = socketVO.getContent();

        // 생성자로 반환값을 생성합니다.
        SocketVO result = new SocketVO(userName, content);
        // 반환
        return result;
    }
}
