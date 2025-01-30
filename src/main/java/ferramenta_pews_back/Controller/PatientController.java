package ferramenta_pews_back.Controller;

import ferramenta_pews_back.DTOs.Patient.PatientGetDTO;
import ferramenta_pews_back.DTOs.Patient.PatientGetListDTO;
import ferramenta_pews_back.DTOs.Patient.PatientPostDTO;
import ferramenta_pews_back.DTOs.User.HealthStaffPostDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Entities.Patient;
import ferramenta_pews_back.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/uuid")
    @Transactional
    public ResponseEntity<PatientGetDTO> getPatientById(@RequestParam UUID uuid) throws BadRequestException{
        return new ResponseEntity<>(patientService.findPatientById(uuid), HttpStatus.OK);
    }

    @GetMapping("/listAll")
    @Transactional
    public ResponseEntity<PatientGetListDTO> getAllPatients(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) throws BadRequestException{
        return new ResponseEntity<>(patientService.findAllPatients(pageNumber,pageSize), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientPostDTO patientPostDTO) throws BadRequestException {
        System.out.println("---------");
        System.out.println(patientPostDTO);
        System.out.println("---------");
        return new ResponseEntity<> (patientService.registerPatient(patientPostDTO), HttpStatus.CREATED);
    }
}
