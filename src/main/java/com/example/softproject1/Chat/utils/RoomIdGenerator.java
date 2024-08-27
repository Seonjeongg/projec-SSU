package com.example.softproject1.Chat.utils;

public class RoomIdGenerator {
    private static Long id = 0L;

    public static Long createId() {
        id += 1;
        return id;
    }
}
