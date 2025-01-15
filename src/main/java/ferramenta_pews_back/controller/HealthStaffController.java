package ferramenta_pews_back.controller;

import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Repositories.HealthStaffRepository;
import ferramenta_pews_back.service.HealtStaffService;
import lombok.RequiredArgsConstructor;
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
    private HealtStaffService healtStaffService;
    @Autowired
    private HealthStaffRepository healthStaffRepository;

    @GetMapping("/all")
    public ResponseEntity<HealthStaff> list(){
        return ResponseEntity.ok(healtStaffService.teste1());
    }

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



