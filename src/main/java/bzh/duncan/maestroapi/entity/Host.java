package bzh.duncan.maestroapi.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Random;

@Entity

public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupeName;
    private int groupCode;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> playerList;


    //Constructor
    public Host(Long id, String groupeName, int groupCode, List<Player> playerList) {
        this.id = id;
        this.groupeName = groupeName;
        this.groupCode = groupCode;
        this.playerList = playerList;
    }

    public Host() {
    }

    // Getters and Setters
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

    public int getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(int groupCode) {
        this.groupCode = groupCode;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
