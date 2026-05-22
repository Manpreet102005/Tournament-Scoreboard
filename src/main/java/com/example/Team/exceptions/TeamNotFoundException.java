package com.example.Team.exceptions;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Integer id) {
        super("Team doesnot exist. id:"+id);
    }
}
