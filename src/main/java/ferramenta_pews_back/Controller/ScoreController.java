package ferramenta_pews_back.Controller;

import ferramenta_pews_back.DTOs.Patient.PatientGetDTO;
import ferramenta_pews_back.DTOs.Score.ScoreGetDTO;
import ferramenta_pews_back.DTOs.Score.ScorePostDTO;
import ferramenta_pews_back.Entities.Score;
import ferramenta_pews_back.Service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/score")
@RequiredArgsConstructor
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @PostMapping
    public ResponseEntity<ScoreGetDTO> getPatientById(@RequestBody ScorePostDTO scorePostDTO) throws BadRequestException {
        System.out.println(scorePostDTO);
        return new ResponseEntity<>(scoreService.registerScore(scorePostDTO), HttpStatus.OK);
    }
}
