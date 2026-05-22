package com.example.Match.exceptions;

public class MatchScheduledInPastException extends IllegalArgumentException{
    public MatchScheduledInPastException() {
        super("Match cannot be scheduled in past.");
    }
}
