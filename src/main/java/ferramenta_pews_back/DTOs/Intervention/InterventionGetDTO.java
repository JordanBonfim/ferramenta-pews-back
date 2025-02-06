package ferramenta_pews_back.DTOs.Intervention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class InterventionGetDTO {
    private UUID uuid;
    private String description;
    private String tempoControleSSVV;
}
