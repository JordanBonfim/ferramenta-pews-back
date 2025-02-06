package ferramenta_pews_back.DTOs.User;

import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true) // Para herança no Lombok
@AllArgsConstructor
@RequiredArgsConstructor
public class HealthStaffPutDTO extends BaseUserDTO{
    private UUID uuid;
    private String role;      // Exemplo: HEALTH_STAFF
    private String name;
    private String document;
    private String specialty;

}
