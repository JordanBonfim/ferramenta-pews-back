package ferramenta_pews_back.Tools;

import ferramenta_pews_back.DTOs.Intervention.InterventionGetDTO;
import ferramenta_pews_back.DTOs.Score.ScoreGetDTO;
import ferramenta_pews_back.DTOs.Score.ScorePostDTO;
import ferramenta_pews_back.DTOs.User.HealthStaffPostDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Entities.Intervention;
import ferramenta_pews_back.Entities.Patient;
import ferramenta_pews_back.Entities.Score;

import java.time.LocalDateTime;

public class ScoreMapper {
    public static Score toEntity(ScorePostDTO dto, Patient patient, Intervention intervention, LocalDateTime time) {
        Score score = Score.builder()
                .fcm(dto.getFcm())
                .frm(dto.getFrm())
                .avaliacaoRespiratoria(dto.getAvaliacaoRespiratoria())
                .avaliacaoCardiovascular(dto.getAvaliacaoCardiovascular())
                .avaliacaoNeurologica(dto.getAvaliacaoNeurologica())
                .final_rating(dto.getFinal_rating())
                .intervention(intervention)
                .nebulizacao(dto.isNebulizacao())
                .createdAt(time)
                .updatedAt(time)
                .eps_Emese(dto.isEps_Emese())
                .patient(patient)
                .build();

        return score;
    }

    public static ScoreGetDTO toEntity(Score dto, InterventionGetDTO intervention) {
        ScoreGetDTO score = ScoreGetDTO.builder()
                .uuid(dto.getUuid())
                .fcm(dto.getFcm())
                .frm(dto.getFrm())
                .avaliacaoRespiratoria(dto.getAvaliacaoRespiratoria())
                .avaliacaoCardiovascular(dto.getAvaliacaoCardiovascular())
                .avaliacaoNeurologica(dto.getAvaliacaoNeurologica())
                .final_rating(dto.getFinal_rating())
                .intervention(intervention)
                .nebulizacao(dto.isNebulizacao())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .eps_Emese(dto.isEps_Emese())
                .build();

        return score;
    }


}
