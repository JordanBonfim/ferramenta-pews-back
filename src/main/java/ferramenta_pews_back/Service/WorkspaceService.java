package ferramenta_pews_back.Service;


import ferramenta_pews_back.DTOs.Workspace.WorkspacePostDTO;
import ferramenta_pews_back.Entities.Workspace;
import ferramenta_pews_back.Repositories.WorkspaceRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;

    public Workspace getByUUID(UUID uuid) throws BadRequestException {
        return  workspaceRepository.findById(uuid).orElseThrow(() -> new BadRequestException("Workspace not found"));
    }

    public Workspace createWorkspace(WorkspacePostDTO dto) {
        Workspace workspace = new Workspace();
        workspace.setName(dto.getName());
        return (workspaceRepository.save(workspace));
    }
}
