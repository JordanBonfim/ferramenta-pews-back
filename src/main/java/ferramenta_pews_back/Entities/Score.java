package ferramenta_pews_back.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(nullable = false)
    private int fcm;  // Frequencia cardiaca por minuto.
    @Column(nullable = false)
    private int frm; // Frequencia respiratoria por minuto.
    @Column(nullable = false)
    private int avaliacaoNeurologica;
    @Column(nullable = false)
    private int avaliacaoCardiovascular;
    @Column(nullable = false)
    private int avaliacaoRespiratoria;
    @Column(nullable = false)
    private boolean nebulizacao; //Nebulização de resgate em 15 minutos
    @Column(nullable = false)
    private boolean eps_Emese; // 3 episódios ou mais de emese no pós operatório
    @Column(nullable = false)
    private int final_rating;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int consciousnessState;

    @ManyToOne
    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name="interventionId")
    private Intervention intervention;
}
