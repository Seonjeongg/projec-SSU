package com.example.softproject1.Chat;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatMember {
    private int articleNo;
    private int memberNo;
    private String role;
    private String memberName;
    private String memberPhotoExtend;
}