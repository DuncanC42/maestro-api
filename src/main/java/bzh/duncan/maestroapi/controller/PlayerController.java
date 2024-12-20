package bzh.duncan.maestroapi.controller;

import bzh.duncan.maestroapi.dto.HostDto;
import bzh.duncan.maestroapi.dto.PlayerDto;
import bzh.duncan.maestroapi.entity.Host;
import bzh.duncan.maestroapi.entity.Player;
import bzh.duncan.maestroapi.repository.PlayerRepository;
import bzh.duncan.maestroapi.response.ApiResponse;
import bzh.duncan.maestroapi.service.host.HostService;
import bzh.duncan.maestroapi.service.player.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;
    private final HostService hostService;

    public PlayerController(PlayerService playerService, HostService hostService) {
        this.playerService = playerService;
        this.hostService = hostService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllPlayers(){
        List<Player> playerList = playerService.listAllPlayer();
        return ResponseEntity.ok(new ApiResponse("List of players : ", playerList));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPlayer(@RequestBody PlayerDto player){
        PlayerDto newPlayer = playerService.createPlayer(player);
        return ResponseEntity.ok(new ApiResponse("New Player created successfully", newPlayer));
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse> joinHost(
            @RequestParam String playerName,
            @RequestParam String groupeName,
            @RequestParam int groupCode
    ) {

        HostDto hostDto = hostService.getHostByGroupeNameAndGroupCode(groupeName, groupCode);

        // Créer l'objet Host dans le Player
        PlayerDto playerDto = playerService.createPlayerByNameAndHost(playerName, hostDto);

        // Ajouter le player dans la liste des joueurs du host
        //hostService.addPlayerToHost(hostDto, playerDto);

        return ResponseEntity.ok(new ApiResponse("Player has joined the host", playerDto));
    }

}
