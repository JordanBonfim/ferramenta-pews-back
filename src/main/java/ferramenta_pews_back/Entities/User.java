package ferramenta_pews_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Table(name = "users") // Renomeia a tabela para evitar conflito com "user"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role; // Exemplo: HEALTH_STAFF, ADMIN


    // Setter para 'username'
    public void setUsername(String username) {
        this.username = username;
    }

    // Setter para 'password'
    public void setPassword(String password) {
        this.password = password;
    }

    // Setter para 'role'
    public void setRole(String role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
