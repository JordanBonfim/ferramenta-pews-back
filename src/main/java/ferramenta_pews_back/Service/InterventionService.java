package ferramenta_pews_back.Service;

import ferramenta_pews_back.DTOs.Intervention.InterventionGetDTO;
import ferramenta_pews_back.DTOs.Intervention.InterventionPostDTO;
import ferramenta_pews_back.Entities.Intervention;
import ferramenta_pews_back.Repositories.InterventionRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InterventionService {

    @Autowired
    InterventionRepository interventionRepository;


    InterventionGetDTO findByUUID(UUID uuid) throws BadRequestException {

        Optional<Intervention> optional = interventionRepository.findById(uuid);
        if(!optional.isPresent()) throw new BadRequestException("Intervention not found");

        Intervention intervention = optional.get();
        InterventionGetDTO interventionGetDTO = InterventionGetDTO.builder()
                .uuid(intervention.getUuid())
                .description(intervention.getDescription())
                .tempoControleSSVV(intervention.getTempoControleSSVV())
                .build();
        return interventionGetDTO;
    }

    Intervention findByDescription(String description){
        Intervention intervention = interventionRepository.findByDescription(description);
        return intervention;
    }
    Intervention registerIntervention(InterventionPostDTO dto) {
        // Verifica se já existe uma intervenção com a descrição fornecida
        Intervention existingIntervention = interventionRepository.findByDescription(dto.getDescription());

        if (existingIntervention != null) {
            // Se a intervenção já existir, retorna a existente
            return existingIntervention;
        }

        // Cria uma nova intervenção
        Intervention newIntervention = new Intervention();
        newIntervention.setDescription(dto.getDescription());
        newIntervention.setTempoControleSSVV(dto.getTempoControleSSVV());

        // Salva a nova intervenção no repositório
        return interventionRepository.save(newIntervention);
    }


}
