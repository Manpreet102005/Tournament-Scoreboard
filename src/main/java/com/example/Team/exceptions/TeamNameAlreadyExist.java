package com.example.Team.exceptions;

public class TeamNameAlreadyExist extends RuntimeException {
    public TeamNameAlreadyExist(String name) {
        super("Team already exist with this name:"+name);
    }
}
