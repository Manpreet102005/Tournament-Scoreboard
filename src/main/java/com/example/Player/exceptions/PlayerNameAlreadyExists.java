package com.example.Player.exceptions;

public class PlayerNameAlreadyExists extends RuntimeException {
    public PlayerNameAlreadyExists(String name) {
        super("Player with name: "+name+" alreday exists.");
    }
}
