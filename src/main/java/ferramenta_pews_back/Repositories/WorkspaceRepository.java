package ferramenta_pews_back.Repositories;


import ferramenta_pews_back.Entities.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkspaceRepository extends JpaRepository<Workspace, UUID> {
}
