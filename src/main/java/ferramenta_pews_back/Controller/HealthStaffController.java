package ferramenta_pews_back.Controller;

import ferramenta_pews_back.DTOs.User.HealthStaffPutDTO;
import ferramenta_pews_back.DTOs.User.LoginRequestDTO;
import ferramenta_pews_back.DTOs.User.LoginResponseDTO;
import ferramenta_pews_back.DTOs.User.HealthStaffPostDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Repositories.HealthStaffRepository;
import ferramenta_pews_back.Service.HealthStaffService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class HealthStaffController {
    @Autowired
    private HealthStaffService healthStaffService;
    @Autowired
    private HealthStaffRepository healthStaffRepository;

    @GetMapping
    public ResponseEntity<HealthStaff> getUserByUsername(@RequestParam String username) throws BadRequestException {
        return new ResponseEntity<>(healthStaffService.getByUserName(username), HttpStatus.OK);
    }

    @GetMapping("/uuid")
    public ResponseEntity<HealthStaff> getUserByUUID(@RequestParam UUID uuid) throws BadRequestException {
        return new ResponseEntity<>(healthStaffService.getByUUID(uuid), HttpStatus.OK);
    }
    @GetMapping("/listAll")
    public ResponseEntity<List<HealthStaff>> getAllUsers() {
        List<HealthStaff> healthStaffList = healthStaffService.getAll();
        return new ResponseEntity<>(healthStaffList, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> HealthStaffLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        System.out.println("Initiating Login");
        if (healthStaffService.login(loginRequestDTO) == null){
            System.out.println("Invalid Credentials");
            return new ResponseEntity<> (null, HttpStatus.UNAUTHORIZED);
        }
        System.out.println("Login in");

        return new ResponseEntity<>(healthStaffService.login(loginRequestDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HealthStaff> createUser(@RequestBody HealthStaffPostDTO healthStaffPostDTO) throws BadRequestException {
        return new ResponseEntity<> (healthStaffService.registerHealthStaff(healthStaffPostDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HealthStaff> updateUser(@RequestBody HealthStaffPutDTO healthStaffPutDTO) throws BadRequestException {
        return new ResponseEntity<> (healthStaffService.updateHealthStaff(healthStaffPutDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HealthStaff> deleteUser(@RequestParam UUID uuid) throws BadRequestException {
        healthStaffService.deleteHealthStaff(uuid);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }




//    @GetMapping("/all")
//    public ResponseEntity<HealthStaff> list(){
//        return ResponseEntity.ok(healtStaffService.teste1());
//    }

//    @GetMapping("/all")
//    public ResponseEntity<List<UserDTO>> list(){
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    @GetMapping("/uuid/{uuid}")
//    public ResponseEntity<UserGetResponseBody> getUserById(@PathVariable UUID uuid) {
//        return new ResponseEntity<> (userService.findByUUID(uuid), HttpStatus.OK) ;
//    }
//
//    @PostMapping
//    public ResponseEntity<UserPostResponseBody> createUser(@RequestBody UserPostRequestBody userPostRequestBody) throws Exception {
//        System.out.println("AAAA");
//        return new ResponseEntity<> (userService.createUser(userPostRequestBody), HttpStatus.CREATED) ;
//    }
//
}



