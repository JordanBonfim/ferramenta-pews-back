package ferramenta_pews_back.DTOs;

import ferramenta_pews_back.Entities.HealthStaff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@RequiredArgsConstructor
public class LoginResponseDTO {
    private String message; // Exemplo: "Login successful"
    private String token;   // Token JWT ou outro
    private UUID uuid;
    private String username;
    private String name;    // Nome do usuário
    private String role;    // Exemplo: HEALTH_STAFF, ADMIN


    public LoginResponseDTO(String message, String token, UUID uuid, String username, String name, String role) {
        this.message = message;
        this.token = token;
        this.uuid = uuid;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", uuid=" + uuid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}
