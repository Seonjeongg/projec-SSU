package com.example.softproject1.Exception;

public class StudentIdAlreadyExistsException extends RuntimeException {
    public StudentIdAlreadyExistsException(String message) {
        super(message);
    }
}