package com.example.Match.exceptions;

public class MatchNotFoundException extends IllegalArgumentException {
    public MatchNotFoundException(Integer id) {
        super("Match does not exists. id: "+id+".");
    }
}
