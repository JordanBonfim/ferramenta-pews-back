package ferramenta_pews_back.DTOs.Patient;

import ferramenta_pews_back.DTOs.Score.ScoreGetDTO;
import ferramenta_pews_back.Entities.Patient;
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
public class PatientGetListDTO {

    private List<PatientGetDTO> patientList;
    private int pageNo;
    private int pageSize;
}