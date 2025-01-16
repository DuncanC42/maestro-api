package bzh.duncan.maestroapi.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HostDto {
    private Long id;
    private String groupeName;
    private int groupeCode;

    @JsonManagedReference
    private List<PlayerDto> playerDtoList;

    public HostDto(String groupeName) {
        this.groupeName = groupeName;
        this.groupeCode = createRandomCode();
    }

    public HostDto(Long id, String groupeName, int groupeCode) {
        this.id = id;
        this.groupeName = groupeName;
        this.groupeCode = groupeCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupeName() {
        return groupeName;
    }

    public void setGroupeName(String groupeName) {
        this.groupeName = groupeName;
    }

    public int getGroupeCode() {
        return groupeCode;
    }

    public void setGroupeCode(int groupeCode) {
        this.groupeCode = groupeCode;
    }

    public List<PlayerDto> getPlayerDtoList() {
        return playerDtoList;
    }

    public void setPlayerDtoList(List<PlayerDto> playerDtoList) {
        this.playerDtoList = playerDtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HostDto hostDto = (HostDto) o;
        return groupeCode == hostDto.groupeCode && Objects.equals(id, hostDto.id) && Objects.equals(groupeName, hostDto.groupeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupeName, groupeCode);
    }

    // Methods
    public int createRandomCode() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

}