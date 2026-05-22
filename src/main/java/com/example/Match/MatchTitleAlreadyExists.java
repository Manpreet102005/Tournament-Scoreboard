package com.example.Match;

public class MatchTitleAlreadyExists extends RuntimeException {
    public MatchTitleAlreadyExists(String title) {
        super("Match title already exist. title: "+title);
    }
}
