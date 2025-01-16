package bzh.duncan.maestroapi.service.host;

import bzh.duncan.maestroapi.dto.HostDto;
import bzh.duncan.maestroapi.dto.PlayerDto;
import bzh.duncan.maestroapi.entity.Host;
import bzh.duncan.maestroapi.entity.Player;

import java.util.List;

public interface IHostService {
    public List<Host> listAllHost();
    public HostDto createHost(HostDto newHost);
    public HostDto getHostByGroupeNameAndGroupCode(String groupeName, int groupCode);
    public void addPlayerToHost(HostDto hostDto, PlayerDto playerDto);

    public List<Player> getPlayersByHostName(String hostName);
}
