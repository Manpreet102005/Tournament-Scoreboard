package com.example.exceptions;

public class PlayerNotFoundException extends IllegalArgumentException{
    public PlayerNotFoundException(Integer id){
        super("Player with id: "+id+" Not Found");
    }
}
