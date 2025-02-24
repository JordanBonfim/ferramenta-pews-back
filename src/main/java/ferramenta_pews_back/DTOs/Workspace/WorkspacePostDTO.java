package ferramenta_pews_back.DTOs.Workspace;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspacePostDTO {
    private String name;
}
