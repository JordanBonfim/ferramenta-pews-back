package ferramenta_pews_back.Service;

import ferramenta_pews_back.DTOs.Intervention.InterventionGetDTO;
import ferramenta_pews_back.DTOs.Patient.PatientGetDTO;
import ferramenta_pews_back.DTOs.Patient.PatientGetListDTO;
import ferramenta_pews_back.DTOs.Patient.PatientPostDTO;
import ferramenta_pews_back.DTOs.Score.ScoreGetDTO;
import ferramenta_pews_back.DTOs.User.HealthStaffPostDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Entities.Patient;
import ferramenta_pews_back.Entities.Score;
import ferramenta_pews_back.Repositories.PatientRepository;
import ferramenta_pews_back.Tools.PatientMapper;
import ferramenta_pews_back.Tools.ScoreMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ScoreService scoreService;
    @Autowired
    InterventionService interventionService;
    ScoreMapper scoreMapper;
    PatientMapper patientMapper;



    public PatientGetListDTO findAllPatients(int pageNumber, int pageSize) throws BadRequestException {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        List<PatientGetDTO> patientGetDTOList = new ArrayList<>();

        Page<Patient> patientList = patientRepository.findAll(pageable);

        for (Patient patient : patientList) {
            List<ScoreGetDTO> scoreList = new ArrayList<>(); // Criação de uma nova lista para cada paciente

            for (Score score : patient.getScoreList()) {
                System.out.println(score.getUuid());
                InterventionGetDTO interventionGetDTO = interventionService.findByUUID(score.getIntervention().getUuid());
                scoreList.add(scoreMapper.toEntity(score, interventionGetDTO));
            }

            patientGetDTOList.add(patientMapper.toEntity(patient, scoreList));
        }

        return new PatientGetListDTO(patientGetDTOList, pageNumber, pageSize);
    }


    public PatientGetDTO findPatientById(UUID id) throws BadRequestException {
        List<ScoreGetDTO> scoreList = new ArrayList<>();
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new BadRequestException("Patient not found"));
        InterventionGetDTO interventionGetDTO = null;
        for(Score score : patient.getScoreList()){

            System.out.println(score.getUuid());
            interventionGetDTO = interventionService.findByUUID(score.getIntervention().getUuid());
            scoreList.add(scoreMapper.toEntity(score, interventionGetDTO));
        }
        return patientMapper.toEntity(patient,scoreList);
    }

    public Patient registerPatient(PatientPostDTO dto) throws BadRequestException {
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

        System.out.println(dto);

        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setBed(dto.getBed());
        patient.setDiagnosis(dto.getDiagnosis());
        patient.setAdmissionDate(dto.getAdmissionDate());
        patient.setBirthDate(dto.getBirthDate());
//        UUID savedId = patientRepository.save(patient).getUuid();
        Patient savedPatient = patientRepository.save(patient);

        scoreService.registerScore(dto.getScore(), savedPatient);
////        Optional<Patient> savedPatient = patientRepository.findById(savedId);
//        Optional<Patient> savedPatient2 = patientRepository.findById(savedPatient.getUuid());
//        System.out.println(savedPatient2.get());
        scoreService.getAllByPatientID(savedPatient.getUuid());
        return savedPatient;
    }
}
