package ferramenta_pews_back.DTOs.User;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true) // Para herança no Lombok
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class HealthStaffPostDTO extends BaseUserDTO {
    private String role;      // Exemplo: HEALTH_STAFF
    private String name;
    private String document;
    private String specialty;
}
