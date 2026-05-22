package com.example.Team.exceptions;

public class TeamAlreadyAssignedException extends RuntimeException {
    public TeamAlreadyAssignedException(Integer playerId, Integer teamId) {
        super("Player with id: "+playerId+" is already assigned to team with id: "+teamId);
    }
}
