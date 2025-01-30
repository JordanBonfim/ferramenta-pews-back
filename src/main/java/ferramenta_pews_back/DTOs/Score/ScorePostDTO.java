package ferramenta_pews_back.DTOs.Score;


import ferramenta_pews_back.DTOs.Intervention.InterventionPostDTO;
import ferramenta_pews_back.Entities.Intervention;
import ferramenta_pews_back.Entities.Patient;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ScorePostDTO {
    private int fcm;                      // Frequencia cardiaca por minuto.
    private int frm;                      // Frequencia respiratoria por minuto.
    private int avaliacaoNeurologica;     // int de 0 - 3
    private int avaliacaoCardiovascular;  // int de 0 - 3
    private int avaliacaoRespiratoria;    // int de 0 - 3
    private boolean nebulizacao;          //Nebulização de resgate em 15 minutos
    private boolean eps_Emese;            // 3 episódios ou mais de emese no pós operatório
    private int final_rating;
    private UUID patientUUID;
    private InterventionPostDTO intervention;
}
