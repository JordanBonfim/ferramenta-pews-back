package ferramenta_pews_back.Repositories;

import ferramenta_pews_back.Entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InterventionRepository extends JpaRepository<Intervention, UUID> {

    Intervention findByDescription(String description);

}
