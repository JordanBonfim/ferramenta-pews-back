package ferramenta_pews_back.Controller;


import ferramenta_pews_back.DTOs.Workspace.WorkspacePostDTO;
import ferramenta_pews_back.Entities.Workspace;
import ferramenta_pews_back.Service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/workspace")
@RequiredArgsConstructor
public class workspaceController {

    @Autowired
    WorkspaceService workspaceService;

    @GetMapping("/uuid")
    @CrossOrigin
    public ResponseEntity<Workspace> getWorkspaceByUUID(@RequestParam UUID uuid) throws BadRequestException {
        return new ResponseEntity<>(workspaceService.getByUUID(uuid), HttpStatus.OK);
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Workspace> createWorkspace(@RequestBody WorkspacePostDTO dto) throws BadRequestException {
        return new ResponseEntity<>(workspaceService.createWorkspace(dto), HttpStatus.OK);

    }

}
