package com.example.exceptions;

public class MatchNotOngoingException extends IllegalStateException {
    public MatchNotOngoingException() {
        super("Can not update scores while match is not ongoing");
    }
}
