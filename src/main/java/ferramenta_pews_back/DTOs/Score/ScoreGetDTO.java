package ferramenta_pews_back.DTOs.Score;

import ferramenta_pews_back.DTOs.Intervention.InterventionGetDTO;
import ferramenta_pews_back.DTOs.Intervention.InterventionPostDTO;
import ferramenta_pews_back.Entities.Intervention;
import ferramenta_pews_back.Entities.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ScoreGetDTO {
    private UUID uuid;
    private int fcm;  // Frequencia cardiaca por minuto.
    private int frm; // Frequencia respiratoria por minuto.
    private int avaliacaoNeurologica;
    private int avaliacaoCardiovascular;
    private int avaliacaoRespiratoria;
    private int consciousnessState;
    private boolean nebulizacao; //Nebulização de resgate em 15 minutos
    private boolean eps_Emese; // 3 episódios ou mais de emese no pós operatório
    private int final_rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private InterventionGetDTO intervention;
}
