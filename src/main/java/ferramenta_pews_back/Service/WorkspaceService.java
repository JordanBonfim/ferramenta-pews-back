package ferramenta_pews_back.Service;


import ferramenta_pews_back.DTOs.Workspace.WorkspacePostDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Entities.User;
import ferramenta_pews_back.Entities.Workspace;
import ferramenta_pews_back.Repositories.HealthStaffRepository;
import ferramenta_pews_back.Repositories.WorkspaceRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkspaceService {

    @Autowired
    HealthStaffRepository healthStaffRepository;
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    @Lazy
    HealthStaffService healthStaffService;

    public Workspace getByUUID(UUID uuid) throws BadRequestException {
        return  workspaceRepository.findById(uuid).orElseThrow(() -> new BadRequestException("Workspace not found"));
    }

    public Workspace createWorkspace(WorkspacePostDTO dto) throws BadRequestException {
        Workspace workspace = new Workspace();
        workspace.setName(dto.getName());

        // Busca o usuário no banco
        HealthStaff healthStaff = healthStaffService.getByUUID(dto.getUser_id());

        // Adiciona o usuário ao workspace
        List<User> users = new ArrayList<>();
        users.add(healthStaff);
        workspace.setUsers(users);

        // Atualiza a relação do lado do usuário (necessário para ManyToMany)
        healthStaff.getWorkspaces().add(workspace);

        // Salva o workspace
        workspaceRepository.save(workspace);

        // Atualiza o usuário
        healthStaffRepository.save(healthStaff);

        return workspace;
    }
}
