package com.example.softproject1.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private Long id;
    private String name; //이름
    private String department; //학부
    private Integer year; //학년

}
