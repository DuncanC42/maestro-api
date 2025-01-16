package bzh.duncan.maestroapi.controller;

import bzh.duncan.maestroapi.dto.HostDto;
import bzh.duncan.maestroapi.entity.Host;
import bzh.duncan.maestroapi.entity.Player;
import bzh.duncan.maestroapi.response.ApiResponse;
import bzh.duncan.maestroapi.service.host.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostController {
    private HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllHost(){
        List<Host> hostList = hostService.listAllHost();
        return ResponseEntity.ok(new ApiResponse("Liste des hosts", hostList));
    }

    @GetMapping("/{hostName}/players")
    public ResponseEntity<ApiResponse> getPlayersByHost(@PathVariable String hostName) {
        List<Player> playerList = hostService.getPlayersByHostName(hostName);
        return ResponseEntity.ok(new ApiResponse("List of players for host " + hostName, playerList));
    }

    @PostMapping("/create/{hostName}")
    public ResponseEntity<ApiResponse> createHost(@PathVariable String hostName){
        HostDto newHost = new HostDto(hostName);
        hostService.createHost(newHost);
        return ResponseEntity.ok(new ApiResponse("Host has been created", newHost));
    }
}
