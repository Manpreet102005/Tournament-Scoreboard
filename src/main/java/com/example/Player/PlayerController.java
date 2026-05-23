package com.example.Player;

import com.example.Match.MatchDTO;
import com.example.Team.TeamRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@Validated
public class PlayerController {
    private final PlayerService playerService;


    PlayerController(PlayerService playerService, PlayerRepository playerRepository, TeamRepository teamRepository){
        this.playerService=playerService;
    }
    //getAll
    @GetMapping()
    public Page<Player> getAllPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return playerService.getAllPlayers(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Player getById(@PathVariable Integer id){
        return playerService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<String> addPlayer(@Valid  @RequestBody Player player){
       return playerService.addPlayer(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Integer id){
        return playerService.deletePlayer(id);
    }

}
