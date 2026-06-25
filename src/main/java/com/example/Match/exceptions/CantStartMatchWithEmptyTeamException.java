package com.example.Match.exceptions;

public class CantStartMatchWithEmptyTeamException extends RuntimeException {
    public CantStartMatchWithEmptyTeamException(){
        super("Match can not be started with empty team");
    }
}
