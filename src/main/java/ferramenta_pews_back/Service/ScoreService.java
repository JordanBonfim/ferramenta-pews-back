package ferramenta_pews_back.Service;

import ferramenta_pews_back.DTOs.Intervention.InterventionGetDTO;
import ferramenta_pews_back.DTOs.Score.ScoreGetDTO;
import ferramenta_pews_back.DTOs.Score.ScorePostDTO;
import ferramenta_pews_back.Entities.Intervention;
import ferramenta_pews_back.Entities.Patient;
import ferramenta_pews_back.Entities.Score;
import ferramenta_pews_back.Repositories.PatientRepository;
import ferramenta_pews_back.Repositories.ScoreRepository;
import ferramenta_pews_back.Tools.ScoreMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ScoreService {
    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    InterventionService interventionService;
    ScoreMapper scoreMapper;

    public Score registerScore(ScorePostDTO dto, Patient patient){
        LocalDateTime time = LocalDateTime.now().withNano(0);
        Intervention intervention = interventionService.registerIntervention(dto.getIntervention());
        Score score = scoreMapper.toEntity(dto, patient, intervention, time);
        return scoreRepository.save(score);
    }

    public ScoreGetDTO registerScore(ScorePostDTO dto) throws BadRequestException {
        LocalDateTime time = LocalDateTime.now().withNano(0);
        Intervention intervention = interventionService.registerIntervention(dto.getIntervention());
        Patient patient = patientRepository.findById(dto.getPatientUUID()).orElseThrow( () -> new BadRequestException("Patient not found"));


        Score score = ScoreMapper.toEntity(dto, patient, intervention, time);
        InterventionGetDTO interventionGetDTO = InterventionGetDTO.builder()
                .tempoControleSSVV(intervention.getTempoControleSSVV())
                .description(intervention.getDescription())
                .uuid(intervention.getUuid())
                .build();


        scoreRepository.save(score);

        ScoreGetDTO scoreGetDTO = scoreMapper.toEntity(score, interventionGetDTO);

        return scoreGetDTO;
    }
    public void getAllByPatientID(UUID uuid) {
        
    }
}
