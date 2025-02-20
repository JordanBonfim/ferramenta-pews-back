package ferramenta_pews_back.Repositories;

import ferramenta_pews_back.Entities.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import java.util.UUID;
public interface PatientRepository extends JpaRepository<Patient, UUID>, JpaSpecificationExecutor<Patient> {

    @Query(value = """
    SELECT p.* FROM patient p
    LEFT JOIN score s ON p.uuid = s.patient_id
    GROUP BY p.uuid
    ORDER BY COALESCE(MAX(s.created_at), '1970-01-01') DESC
    """, nativeQuery = true)
    Page<Patient> findAllOrderedByLatestScoreNative(Pageable pageable);






}
