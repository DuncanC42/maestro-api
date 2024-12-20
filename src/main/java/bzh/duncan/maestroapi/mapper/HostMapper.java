package bzh.duncan.maestroapi.mapper;

import bzh.duncan.maestroapi.dto.HostDto;
import bzh.duncan.maestroapi.entity.Host;

import java.util.ArrayList;

public class HostMapper {

    public static Host mapHostDtoToHost(HostDto hostDto){
        Host host = new Host();
        host.setId(hostDto.getId());
        host.setGroupeName(hostDto.getGroupeName());
        host.setGroupCode(hostDto.getGroupeCode());
        host.setPlayerList(new ArrayList<>());
        return host;
    }

    public static HostDto mapHostToHostDto(Host host){
        return new HostDto(
                host.getId(),
                host.getGroupeName(),
                host.getGroupCode()
        );
    }

}
