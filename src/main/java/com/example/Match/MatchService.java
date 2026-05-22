package com.example.Match;

import com.example.Team.Team;
import com.example.Team.TeamRepository;
import com.example.exceptions.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class MatchService {

    private MatchRepository matchRepository;
    private TeamRepository teamRepository;

    public MatchService(MatchRepository matchRepository,TeamRepository teamRepository) {
        this.teamRepository=teamRepository;
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Integer id) {
        return matchRepository.findById(id).orElseThrow(()->new MatchNotFoundException(id));
    }

    public ResponseEntity<String> addMatch(Match match, Integer teamAId, Integer teamBId){
        if(match.getMatchDateTime().isBefore(LocalDateTime.now())){
            throw new MatchScheduledInPastException();
        }
        if(matchRepository.existsByMatchTitle(match.getMatchTitle())){
            throw new MatchTitleAlreadyExists(match.getMatchTitle());
        }
        Team teamA=teamRepository.findById(teamAId).orElseThrow(()->
                new TeamNotFoundException(teamAId)
        );
        Team teamB=teamRepository.findById(teamBId).orElseThrow(()->
                new TeamNotFoundException(teamBId)
        );

        match.setTeamA(teamA);
        match.setTeamB(teamB);
        matchRepository.save(match);
        return ResponseEntity.ok().body("Match Added Successfully. Assigned Match Id: "+match.getMatchId());
    }

    public ResponseEntity<String> removeMatch(Integer id){
        getMatchById(id);
        matchRepository.deleteById(id);
        return ResponseEntity.ok().body("Removed Successfully");
    }

    public ResponseEntity<String> rescheduleMatch(Integer matchId, LocalDateTime newDateTime) {
        Match match=getMatchById(matchId);

        if(newDateTime.isBefore(LocalDateTime.now())){
            throw new MatchScheduledInPastException();
        }
        match.setMatchDateTime(newDateTime);
        matchRepository.save(match);
        return ResponseEntity.ok().body("Match Rescheduled Successfully.");
    }

    public ResponseEntity<String> updateTeamScore(Integer matchId, Integer teamId, Integer score) {
        Match match=getMatchById(matchId);
        if(match.getMatchStatus()!=MatchStatus.SCHEDULED){
            throw new MatchStatusException("Can not update scores before match has started.");
        }
        else if(match.getMatchStatus()!=MatchStatus.COMPLETED){
            throw new MatchStatusException("Can not update scores after match has completed.");
        }
        if(score<0 || score>100){
            throw new InvalidScoreException();
        }
        if(match.getTeamA().getTeamId().equals(teamId)){
            match.setTeamAScore(score);
        }
        else if(match.getTeamB().getTeamId().equals(teamId)){
            match.setTeamBScore(score);
        }
        else {
            throw new TeamNotPartOfMatchException(teamId,matchId);
        }
        matchRepository.save(match);
        return ResponseEntity.ok().body("Team Score Updated Successfully");
    }

    public ResponseEntity<String> startMatch(Integer matchId) {
        Match match=getMatchById(matchId);
        if(match.getMatchStatus().equals(MatchStatus.ONGOING)){
            throw new MatchStatusException("Match Already ONGOING");
        }
        if(match.getMatchStatus().equals(MatchStatus.COMPLETED)){
            throw new MatchStatusException("Can not update scores of COMPLETED match");
        }
        match.setMatchStatus(MatchStatus.ONGOING);
        matchRepository.save(match);
        return ResponseEntity.ok().body("Match with id: "+matchId+" is now ONGOING.");
    }
    @Transactional
    public ResponseEntity<String> endMatch(Integer matchId) {
        Match match = getMatchById(matchId);
        if (!match.getMatchStatus().equals(MatchStatus.ONGOING)) {
            throw new MatchStatusException("Match must be ONGOING to end it. Current status: " + match.getMatchStatus());
        }
        Team teamA=match.getTeamA();
        Team teamB=match.getTeamB();

        teamA.setTotalScore(teamA.getTotalScore()+match.getTeamAScore());
        teamB.setTotalScore(teamB.getTotalScore()+match.getTeamBScore());

        match.setMatchStatus(MatchStatus.COMPLETED);
        teamRepository.saveAll(List.of(teamA,teamB));
        matchRepository.save(match);
        return ResponseEntity.ok().body("Match with id: "+matchId+" is now COMPLETED ");
    }
}

