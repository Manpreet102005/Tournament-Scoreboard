package com.example.Team.exceptions;

public class TeamNameAlreadyExist extends RuntimeException {
    public TeamNameAlreadyExist(String name) {
        super("team already exist with this name:"+name);
    }
}
