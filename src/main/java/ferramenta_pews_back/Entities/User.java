package ferramenta_pews_back.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
