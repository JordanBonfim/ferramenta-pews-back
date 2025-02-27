package ferramenta_pews_back.DTOs.Patient;

import ferramenta_pews_back.DTOs.Score.ScoreGetDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PatientGetDTO {
    private UUID uuid;
    private String name;
    private String diagnosis;
    private int bed;
    private LocalDate birthDate;
    private LocalDate admissionDate;
    private List<ScoreGetDTO> scoreList;
}