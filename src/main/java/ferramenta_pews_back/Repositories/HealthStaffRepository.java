package ferramenta_pews_back.Repositories;

import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface HealthStaffRepository extends JpaRepository<HealthStaff, UUID> {
    HealthStaff findByUsernameAndPassword(String username, String password);
    Optional<HealthStaff> findByUsername(String username);

}
