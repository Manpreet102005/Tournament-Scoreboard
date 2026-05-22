package com.example.exceptions;

public class MatchStatusException extends IllegalStateException {
    public MatchStatusException(String message) {
        super(message);
    }
}
