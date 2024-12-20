package bzh.duncan.maestroapi.service.player;

import bzh.duncan.maestroapi.dto.HostDto;
import bzh.duncan.maestroapi.dto.PlayerDto;
import bzh.duncan.maestroapi.entity.Host;
import bzh.duncan.maestroapi.entity.Player;
import bzh.duncan.maestroapi.mapper.PlayerMapper;
import bzh.duncan.maestroapi.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService{

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> listAllPlayer() {
        return playerRepository.findAll();

    }

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player = PlayerMapper.mapPlayerDtoToPlayer(playerDto);
        Player savedPlayer = playerRepository.save(player);
        return PlayerMapper.mapPlayerToPlayerDto(savedPlayer);
    }

    @Override
    public PlayerDto createPlayerByNameAndHost(String playerName, HostDto host) {
        PlayerDto playerDto = new PlayerDto(playerName, host);
        Player player = PlayerMapper.mapPlayerDtoToPlayer(playerDto);
        playerRepository.save(player);
        return playerDto;
    }


}
