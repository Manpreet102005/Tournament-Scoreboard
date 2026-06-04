package com.example.Player;

public class PlayerDTO {
    Integer playerId;
    String playerName;
    String teamName;

    public PlayerDTO(Integer playerId, String playerName, String teamName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.teamName = teamName;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTeamName() {
        return teamName;
    }
}
