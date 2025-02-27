package ferramenta_pews_back.Service;

import ferramenta_pews_back.DTOs.Intervention.InterventionGetDTO;
import ferramenta_pews_back.DTOs.Patient.PatientGetDTO;
import ferramenta_pews_back.DTOs.Patient.PatientGetListDTO;
import ferramenta_pews_back.DTOs.Patient.PatientPostDTO;
import ferramenta_pews_back.DTOs.Score.ScoreGetDTO;
import ferramenta_pews_back.Entities.Patient;
import ferramenta_pews_back.Entities.Score;
import ferramenta_pews_back.Repositories.PatientRepository;
import ferramenta_pews_back.Tools.PatientMapper;
import ferramenta_pews_back.Tools.ScoreMapper;
import jakarta.persistence.criteria.Predicate;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;


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




    public PatientGetListDTO findPatientsWithSorting(
            int pageNumber, int pageSize, String sortBy, String sortDirection
    ) throws BadRequestException {

        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);

        List<PatientGetDTO> patientGetDTOList = new ArrayList<>();
        Page<Patient> patientList;



        switch (sortBy){
            case "":
                System.out.println("");
                break;

        }


        if ("latestScore".equalsIgnoreCase(sortBy)) {
            patientList = patientRepository.findAllOrderedByLatestScoreNative(pageable);
        } else {
            // Caso contrário, usamos a ordenação padrão
            patientList = patientRepository.findAll(pageable);
        }


        for (Patient patient : patientList) {
            List<Score> scores = patient.getScoreList();

            if (!scores.isEmpty()) {
                Score latestScore = scores.stream()
                        .max(Comparator.comparing(Score::getCreatedAt))
                        .orElse(null);

                if (latestScore != null) {
                    InterventionGetDTO interventionGetDTO = interventionService.findByUUID(latestScore.getIntervention().getUuid());
                    ScoreGetDTO latestScoreDTO = scoreMapper.toEntity(latestScore, interventionGetDTO);
                    patientGetDTOList.add(patientMapper.toEntity(patient, List.of(latestScoreDTO)));
                }
            } else {
                patientGetDTOList.add(patientMapper.toEntity(patient, new ArrayList<>()));
            }
        }


        return new PatientGetListDTO(patientGetDTOList, pageNumber, pageSize);
    }



    public PatientGetListDTO findAllPatientsTestFilters(int pageNumber, int pageSize, String name) throws BadRequestException {
        List<PatientGetDTO> patientGetDTOList = new ArrayList<>();

        // Criação do PageRequest para definir a paginação
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);

        // Realizando a consulta com filtro dinâmico
        Page<Patient> patientList = patientRepository.findAll((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction(); // Predicado inicial (sempre verdadeiro)

            // Verificando se o filtro de 'name' foi passado e aplicando o filtro de busca
            if (StringUtils.hasText(name)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            return predicate;
        }, pageable);



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




//    public PatientGetListDTO findPatientsWithLatestScore(int pageNumber, int pageSize) throws BadRequestException {


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
        Patient savedPatient = patientRepository.save(patient);

        if (dto.getScore() != null) {
            scoreService.registerScore(dto.getScore(), savedPatient);
        }

        scoreService.getAllByPatientID(savedPatient.getUuid());
        return savedPatient;
    }

}
