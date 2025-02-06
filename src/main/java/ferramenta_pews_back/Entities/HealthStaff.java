package ferramenta_pews_back.Entities;

import jakarta.persistence.Entity;
import lombok.*;

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
