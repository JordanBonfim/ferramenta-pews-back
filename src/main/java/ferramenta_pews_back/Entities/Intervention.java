package ferramenta_pews_back.Entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(nullable = false, unique = true)
    private String description;
    @Column(nullable = false)
    private String TempoControleSSVV;

    @OneToMany(mappedBy = "intervention")
    private List<Score> scores;

}
