package bzh.duncan.maestroapi.repository;

import bzh.duncan.maestroapi.dto.HostDto;
import bzh.duncan.maestroapi.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    Host findByGroupeNameAndGroupCode(String groupeName, int groupCode);
}
