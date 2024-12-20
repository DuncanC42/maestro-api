package bzh.duncan.maestroapi.dto;

import bzh.duncan.maestroapi.entity.Host;
import java.util.Objects;

public class PlayerDto {
    private Long id;
    private String pseudo;
    private HostDto host;

    public PlayerDto(String pseudo, HostDto host) {
        this.pseudo = pseudo;
        this.host = host;
    }

    public PlayerDto() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public HostDto getHost() {
        return host;
    }

    public void setHost(HostDto host) {
        this.host = host;
    }

    // toString method
    @Override
    public String toString() {
        return "PlayerDto{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto playerDto = (PlayerDto) o;
        return Objects.equals(id, playerDto.id) &&
                Objects.equals(pseudo, playerDto.pseudo);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, pseudo);
    }
}