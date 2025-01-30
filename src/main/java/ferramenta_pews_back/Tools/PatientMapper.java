package ferramenta_pews_back.Tools;

import ferramenta_pews_back.DTOs.Patient.PatientGetDTO;
import ferramenta_pews_back.DTOs.Score.ScoreGetDTO;
import ferramenta_pews_back.DTOs.Score.ScorePostDTO;
import ferramenta_pews_back.Entities.Intervention;
import ferramenta_pews_back.Entities.Patient;
import ferramenta_pews_back.Entities.Score;

import java.time.LocalDateTime;
import java.util.List;

public class PatientMapper {

    public static PatientGetDTO toEntity(Patient dto, List<ScoreGetDTO> scoreList) {
        PatientGetDTO patientGetDTO = PatientGetDTO.builder()
                .uuid(dto.getUuid())
                .bed(dto.getBed())
                .name(dto.getName())
                .admissionDate(dto.getAdmissionDate())
                .birthDate(dto.getBirthDate())
                .diagnosis(dto.getDiagnosis())
                .scoreList(scoreList)
                .build();
        return patientGetDTO;
    }
}
