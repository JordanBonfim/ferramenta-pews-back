package ferramenta_pews_back.DTOs.Patient;



import ferramenta_pews_back.DTOs.Score.ScorePostDTO;
import ferramenta_pews_back.Entities.Score;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PatientPostDTO {
    private String name;
    private String diagnosis;
    private int bed;
    private LocalDate birthDate;
    private LocalDate admissionDate;
    private ScorePostDTO score;
}
