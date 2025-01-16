package bzh.duncan.maestroapi.service.host;

import bzh.duncan.maestroapi.dto.HostDto;
import bzh.duncan.maestroapi.dto.PlayerDto;
import bzh.duncan.maestroapi.entity.Host;
import bzh.duncan.maestroapi.entity.Player;
import bzh.duncan.maestroapi.mapper.HostMapper;
import bzh.duncan.maestroapi.mapper.PlayerMapper;
import bzh.duncan.maestroapi.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostService implements IHostService{

    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Host> listAllHost() {
        return hostRepository.findAll();
    }

    @Override
    public HostDto createHost(HostDto newHostDto) {
        Host host = HostMapper.mapHostDtoToHost(newHostDto);
        hostRepository.save(host);
        return newHostDto;
    }

    @Override
    public HostDto getHostByGroupeNameAndGroupCode(String groupeName, int groupCode) {
        return HostMapper.mapHostToHostDto(hostRepository.findByGroupeNameAndGroupCode(groupeName, groupCode));
    }


    @Override
    public void addPlayerToHost(HostDto hostDto, PlayerDto playerDto) {
        Host host = HostMapper.mapHostDtoToHost(hostDto);
        Player player = PlayerMapper.mapPlayerDtoToPlayer(playerDto);
        host.getPlayerList().add(player);
        hostRepository.save(host);
    }

    @Override
    public List<Player> getPlayersByHostName(String groupeName) {
        Host host = hostRepository.findByGroupeName((groupeName));
        if (host != null) {
            return host.getPlayerList();
        }
        return List.of();
    }
}
