package bzh.duncan.maestroapi.entity;

import jakarta.persistence.*;

@Entity

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pseudo;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;


    // Constructor
    public Player() {
    }

    public Player(Long id, String pseudo, Host host) {
        this.id = id;
        this.pseudo = pseudo;
        this.host = host;
    }

    // Getters and Setters :
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

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
