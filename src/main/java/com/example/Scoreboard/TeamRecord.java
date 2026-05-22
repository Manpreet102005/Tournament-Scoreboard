package com.example.Scoreboard;

class TeamRecord {
    private Integer rank;
    private Integer teamId;
    private String teamName;
    private Integer totalScore;
    private Integer matchesPlayed;
    private Integer wins;

    public TeamRecord(Integer rank, Integer teamId, String teamName, Integer totalScore, Integer matchesPlayed, Integer wins) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.totalScore = totalScore;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }
}
