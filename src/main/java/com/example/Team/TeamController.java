package com.example.Team;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@Validated
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Integer id){
        return teamService.getTeamById(id);
    }

    @GetMapping()
    public List<Team> getAllTeams(){

        return teamService.getAllTeams();
    }

    @PostMapping()
    public ResponseEntity<String> addTeam(@RequestBody Team team){
        return teamService.addTeam(team);
    }

    @PostMapping("/{teamId}/player/{playerId}")
    public ResponseEntity<String> assignPlayerToTeam(@PathVariable Integer teamId,
        @PathVariable Integer playerId){

        return teamService.assignPlayerToTeam(teamId,playerId);
    }

    @DeleteMapping("{teamId}")
    public ResponseEntity<String> removeTeam(@PathVariable Integer teamId){
        return teamService.removeTeam(teamId);
    }

}
