package bzh.duncan.maestroapi.service.player;

import bzh.duncan.maestroapi.dto.HostDto;
import bzh.duncan.maestroapi.dto.PlayerDto;
import bzh.duncan.maestroapi.entity.Host;
import bzh.duncan.maestroapi.entity.Player;

import java.util.List;

public interface IPlayerService {
    public List<Player> listAllPlayer();
    public PlayerDto createPlayer(PlayerDto playerDto);
    public PlayerDto createPlayerByNameAndHost(String playerName, HostDto hostDto);

}
