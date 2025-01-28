package ferramenta_pews_back.Entities;

import ferramenta_pews_back.DTOs.BaseUserDTO;
import ferramenta_pews_back.DTOs.HealthStaffPostDTO;
import ferramenta_pews_back.DTOs.HealthStaffPutDTO;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class HealthStaff extends User {

    private String name;
    private String document;
    private String specialty;

}
