package bzh.duncan.maestroapi.mapper;

import bzh.duncan.maestroapi.dto.PlayerDto;
import bzh.duncan.maestroapi.entity.Player;

public class PlayerMapper {


    public static Player mapPlayerDtoToPlayer(PlayerDto playerDto){
        Player player = new Player();
        player.setId(playerDto.getId());
        player.setPseudo(playerDto.getPseudo());
        player.setHost(HostMapper.mapHostDtoToHost(playerDto.getHost()));
        return player;
    }

    public static PlayerDto mapPlayerToPlayerDto(Player player){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setPseudo(player.getPseudo());
        playerDto.setHost(HostMapper.mapHostToHostDto(player.getHost()));
        return playerDto;
    }

}
