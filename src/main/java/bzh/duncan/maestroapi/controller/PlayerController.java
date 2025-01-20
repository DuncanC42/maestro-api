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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;
    private final HostService hostService;
    private final SimpMessagingTemplate messagingTemplate;

    public PlayerController(PlayerService playerService, HostService hostService,
                            SimpMessagingTemplate messagingTemplate) {
        this.playerService = playerService;
        this.hostService = hostService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse> joinHost(
            @RequestParam String playerName,
            @RequestParam String groupeName,
            @RequestParam int groupCode
    ) {
        try {
            HostDto hostDto = hostService.getHostByGroupeNameAndGroupCode(groupeName, groupCode);
            PlayerDto playerDto = playerService.createPlayerByNameAndHost(playerName, hostDto);

            // Envoyer une notification WebSocket
            messagingTemplate.convertAndSend(
                    "/topic/game/" + groupeName + "/players",
                    new WebSocketController.PlayerUpdate("JOIN", playerDto, groupeName)
            );

            return ResponseEntity.ok(new ApiResponse("Player has joined the host", playerDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("Error joining host: " + e.getMessage(), null));
        }
    }

    // Delete player by its pseudo and his group name
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deletePlayer(
            @RequestParam String playerPseudo,
            @RequestParam String hostName
    ) {
        try {
            // Envoyer une notification WebSocket
            HostDto hostDto = new HostDto(hostName);
            PlayerDto playerDto = new PlayerDto(playerPseudo, hostDto);
            messagingTemplate.convertAndSend(
                    "/topic/game/" + hostName + "/players",
                    new WebSocketController.PlayerUpdate("LEAVE", playerDto, hostName)
            );
            playerService.removePlayerFromGroup(playerPseudo, hostName);
            return ResponseEntity.ok(new ApiResponse("Player has been removed", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("Error removing player: " + e.getMessage(), null));
        }
    }



}
