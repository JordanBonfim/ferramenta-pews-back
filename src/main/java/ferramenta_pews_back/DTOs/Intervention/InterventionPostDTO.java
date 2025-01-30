package ferramenta_pews_back.DTOs.Intervention;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class InterventionPostDTO {
    private String description;
    private String tempoControleSSVV;
}
