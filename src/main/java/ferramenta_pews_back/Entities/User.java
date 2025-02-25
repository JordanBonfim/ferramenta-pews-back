package ferramenta_pews_back.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
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
    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany
    @JoinTable(name = "user_workspaces",
            joinColumns = @JoinColumn (name = "user_fk"),
            inverseJoinColumns = @JoinColumn(name = "workspace_fk"))
    private List<Workspace> workspaces;
}
