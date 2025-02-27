package ferramenta_pews_back.Controller;


import ferramenta_pews_back.DTOs.Score.ScoreGetDTO;
import ferramenta_pews_back.DTOs.Score.ScorePostDTO;
import ferramenta_pews_back.Service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/score")
@RequiredArgsConstructor
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @PostMapping
    @CrossOrigin
    public ResponseEntity<ScoreGetDTO> getPatientById(@RequestBody ScorePostDTO scorePostDTO) throws BadRequestException {
        System.out.println(scorePostDTO);
        return new ResponseEntity<>(scoreService.registerScore(scorePostDTO), HttpStatus.OK);
    }


    @GetMapping("/patient")
    @CrossOrigin
    public ResponseEntity<List<ScoreGetDTO>> getAllByPatientID(@RequestParam UUID uuid){
        return new ResponseEntity<>(scoreService.getAllByPatientID(uuid), HttpStatus.OK);
    }

}
