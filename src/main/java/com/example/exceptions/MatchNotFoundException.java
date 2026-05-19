package com.example.exceptions;

import java.rmi.NoSuchObjectException;

public class MatchNotFoundException extends IllegalArgumentException {
    public MatchNotFoundException(Integer id) {
        super("Match does not exists. id: "+id+".");
    }
}
