package ferramenta_pews_back.DTOs;

import ferramenta_pews_back.Entities.HealthStaff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginResponseDTO {
    private String message; // Exemplo: "Login successful"
    private String token;   // Token JWT ou outro
    private UUID uuid;
    private String username;
    private String name;    // Nome do usuário
    private String role;    // Exemplo: HEALTH_STAFF, ADMIN

}
