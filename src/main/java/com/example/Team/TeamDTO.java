package com.example.Team;

public class TeamDTO {
    private final Integer rank;
    private final String teamName;
    private final Integer totalScore;
    private final Integer matchesPlayed;
    private final Integer wins;
    private final Double winRatio;

    public TeamDTO(Integer rank, String teamName, Integer totalScore, Integer matchesPlayed, Integer wins, Double winRatio) {
        this.rank=rank;
        this.teamName = teamName;
        this.totalScore = totalScore;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.winRatio=winRatio;
    }

    public Integer getRank() {
        return rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public Integer getWins() {
        return wins;
    }

    public Double getWinRatio() {
        return winRatio;
    }
}
