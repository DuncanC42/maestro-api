package bzh.duncan.maestroapi.service.player;

import bzh.duncan.maestroapi.dto.HostDto;
import bzh.duncan.maestroapi.dto.PlayerDto;
import bzh.duncan.maestroapi.entity.Host;
import bzh.duncan.maestroapi.entity.Player;
import bzh.duncan.maestroapi.mapper.PlayerMapper;
import bzh.duncan.maestroapi.repository.HostRepository;
import bzh.duncan.maestroapi.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService{

    private final PlayerRepository playerRepository;
    private final HostRepository hostRepository;

    public PlayerService(PlayerRepository playerRepository, HostRepository hostRepository) {
        this.playerRepository = playerRepository;
        this.hostRepository = hostRepository;
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

    @Override
    public void removePlayerFromGroup(String playerPseudo, String hostName) {
        Host host = hostRepository.findByGroupeName(hostName);
        if (host != null) {
            // find the player in the host by its pseudo
            Player player = host.getPlayerList().stream()
                    .filter(p -> p.getPseudo().equals(playerPseudo))
                    .findFirst()
                    .orElse(null);

            if (player != null) {
                host.getPlayerList().remove(player); // Remove player from host's list
                playerRepository.delete(player); // Delete player from repository
            }
        }
    }
}
