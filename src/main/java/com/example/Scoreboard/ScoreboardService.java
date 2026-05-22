package com.example.Scoreboard;

import com.example.Match.MatchRepository;
import com.example.Team.Team;
import com.example.Team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreboardService {
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public ScoreboardService(TeamRepository teamRepository,MatchRepository matchRepository) {
        this.teamRepository=teamRepository;
        this.matchRepository = matchRepository;
    }

    public List<TeamRecord> showScoreboard() {
        List<TeamRecord> scoreboardList=new ArrayList<>();
        List<Team> teamList=teamRepository.findAll();
        teamList.sort(
                (a,b)->Integer.compare(b.getTotalScore(),a.getTotalScore())
        );

        for(int i=0;i<teamList.size();i++){
            Team team=teamList.get(i);
            scoreboardList.add(
                    new TeamRecord(
                            i+1,
                            team.getTeamId(),
                            team.getTeamName(),
                            team.getTotalScore(),
                            team.getMatchesPlayed(),
                            team.getWins()
                    )
            );
        }
        return scoreboardList;
    }

}
