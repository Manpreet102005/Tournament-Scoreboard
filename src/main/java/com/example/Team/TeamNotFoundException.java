package com.example.Team;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Integer id) {
        super("Team doesnot exist. id:"+id);
    }
}
