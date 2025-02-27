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
    @CrossOrigin
    @Transactional
    public ResponseEntity<PatientGetDTO> getPatientById(@RequestParam UUID uuid) throws BadRequestException{
        return new ResponseEntity<>(patientService.findPatientById(uuid), HttpStatus.OK);
    }

    @GetMapping("/listAll")
    @CrossOrigin
    @Transactional
    public ResponseEntity<PatientGetListDTO> getAllPatients(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "name", required = false) String name
    ) throws BadRequestException {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(patientService.findAllPatients(pageNumber, pageSize), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(patientService.findAllPatientsTestFilters(pageNumber, pageSize, name), HttpStatus.OK);
        }
    }

    @GetMapping("/listLatestScores")
    @CrossOrigin
    @Transactional
    public ResponseEntity<PatientGetListDTO> getPatientsWithLatestScore(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection
    ) throws BadRequestException {
        return new ResponseEntity<>(
                patientService.findPatientsWithSorting(pageNumber, pageSize, sortBy, sortDirection),
                HttpStatus.OK
        );
    }



//    @GetMapping("/listAll")
//    @Transactional
//    public ResponseEntity<PatientGetListDTO> getAllPatients(
//            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
//    ) throws BadRequestException{
//        return new ResponseEntity<>(patientService.findAllPatients(pageNumber,pageSize), HttpStatus.OK);
//    }
//
//    @GetMapping("/listAll2")
//    @Transactional
//    public ResponseEntity<PatientGetListDTO> getAllPatientsWithFilters(
//            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
//            @RequestParam(value = "name", required = false) String name
//    ) throws BadRequestException{
//        return new ResponseEntity<>(patientService.findAllPatientsTestFilters(pageNumber,pageSize, name), HttpStatus.OK);
//    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Patient> createPatient(@RequestBody PatientPostDTO patientPostDTO) throws BadRequestException {
        System.out.println("---------");
        System.out.println(patientPostDTO);
        System.out.println("---------");
        return new ResponseEntity<> (patientService.registerPatient(patientPostDTO), HttpStatus.CREATED);
    }
}
