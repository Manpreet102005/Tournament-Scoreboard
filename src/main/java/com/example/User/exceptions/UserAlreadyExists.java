package com.example.User.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String name) {
        super("Username Already Exists. Username:"+ name);
    }
}
