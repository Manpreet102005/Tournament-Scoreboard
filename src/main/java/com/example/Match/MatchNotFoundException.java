package com.example.Match;

public class MatchNotFoundException extends IllegalArgumentException {
    public MatchNotFoundException(Integer id) {
        super("Match does not exists. id: "+id+".");
    }
}
