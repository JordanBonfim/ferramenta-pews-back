package ferramenta_pews_back.Repositories;

import ferramenta_pews_back.Entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScoreRepository extends JpaRepository<Score, UUID> {
    List<Score> findAllByPatientUuidOrderByUpdatedAtDesc(UUID uuid);

}
