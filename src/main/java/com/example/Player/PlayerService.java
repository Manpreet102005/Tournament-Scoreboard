package com.example.Player;


import com.example.Player.exceptions.PlayerNameAlreadyExists;
import com.example.Player.exceptions.PlayerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    private Player getById(Integer id) {
        return playerRepository.findById(id).orElseThrow(()->new PlayerNotFoundException(id));
    }

    public PlayerDTO getDTOById(Integer id) {
        Player player=playerRepository.findById(id).orElseThrow(()->new PlayerNotFoundException(id));
        return toPlayerDTO(player);
    }

    public Page<PlayerDTO> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable).map(this::toPlayerDTO);
    }

    public ResponseEntity<String> addPlayer(Player player) {
        if(playerRepository.existsByName(player.getName())){
            throw new PlayerNameAlreadyExists(player.getName());
        }
        playerRepository.save(player);
        return ResponseEntity.ok().body("Player added successfully. Assigned Id: "+player.getId());
    }

    public ResponseEntity<String> deletePlayer(Integer id) {
        if(!playerRepository.existsById(id)){
            throw new PlayerNotFoundException(id);
        }
        playerRepository.deleteById(id);
        return ResponseEntity.ok().body("Player with id: "+id+" deleted successfully");
    }
    private PlayerDTO toPlayerDTO(Player player){
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getTeam().getTeamName()
        );
    }
}
