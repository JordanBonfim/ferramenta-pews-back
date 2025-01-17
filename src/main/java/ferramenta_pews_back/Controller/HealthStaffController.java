package ferramenta_pews_back.Controller;

import ferramenta_pews_back.DTOs.LoginRequestDTO;
import ferramenta_pews_back.DTOs.LoginResponseDTO;
import ferramenta_pews_back.DTOs.HealthStaffPostDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Exception.InvalidCredentialsException;
import ferramenta_pews_back.Repositories.HealthStaffRepository;
import ferramenta_pews_back.Service.HealthStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class HealthStaffController {
    @Autowired
    private HealthStaffService healthStaffService;
    @Autowired
    private HealthStaffRepository healthStaffRepository;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> HealthStaffLogin(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception{
        System.out.println("Initiating Login");
        if (healthStaffService.login(loginRequestDTO) == null){
            System.out.println("Invalid Credentials");
            return new ResponseEntity<> (null, HttpStatus.UNAUTHORIZED);
//              throw new InvalidCredentialsException("Invalid login credentials.");
        }
        System.out.println("Login in");

        return new ResponseEntity<>(healthStaffService.login(loginRequestDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HealthStaff> createUser(@RequestBody HealthStaffPostDTO healthStaffPostDTO) throws Exception {
        return new ResponseEntity<> (healthStaffService.registerHealthStaff(healthStaffPostDTO), HttpStatus.CREATED) ;
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



