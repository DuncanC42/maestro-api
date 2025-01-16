package bzh.duncan.maestroapi.controller;

import bzh.duncan.maestroapi.dto.PlayerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebSocketController {
    private final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/game/{groupName}/join")
    @SendTo("/topic/game/{groupName}/players")
    public PlayerDto handlePlayerJoin(@DestinationVariable String groupName, PlayerDto player) {
        logger.info("Player joining group: {} - Player: {}", groupName, player);

        // Créer un objet de mise à jour
        PlayerUpdate update = new PlayerUpdate(
                "JOIN",
                player,
                groupName
        );

        // Envoyer la mise à jour
        messagingTemplate.convertAndSend("/topic/game/" + groupName + "/players", update);

        return player;
    }

    // Classe interne pour la structure des mises à jour
    static class PlayerUpdate {
        private String type;
        private PlayerDto player;
        private String groupName;

        public PlayerUpdate(String type, PlayerDto player, String groupName) {
            this.type = type;
            this.player = player;
            this.groupName = groupName;
        }

        // Getters et setters
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public PlayerDto getPlayer() { return player; }
        public void setPlayer(PlayerDto player) { this.player = player; }
        public String getGroupName() { return groupName; }
        public void setGroupName(String groupName) { this.groupName = groupName; }
    }

}
