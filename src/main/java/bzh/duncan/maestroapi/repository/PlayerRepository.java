package bzh.duncan.maestroapi.repository;

import bzh.duncan.maestroapi.dto.PlayerDto;
import bzh.duncan.maestroapi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}

