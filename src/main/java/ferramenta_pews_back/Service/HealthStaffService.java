package ferramenta_pews_back.Service;


import ferramenta_pews_back.DTOs.LoginRequestDTO;
import ferramenta_pews_back.DTOs.LoginResponseDTO;
import ferramenta_pews_back.DTOs.HealthStaffPostDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Entities.User;
import ferramenta_pews_back.Repositories.HealthStaffRepository;
import ferramenta_pews_back.Tools.HealthStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthStaffService {
    @Autowired
    HealthStaffRepository healthStaffRepository;

    @Autowired
    HealthStaffMapper healthStaffMapper;

    private int validateFields(Object... fields) {
        for (Object field : fields) {
            if (field == null) {
                return 0;
            }
        }
        return 1;
    }


    public HealthStaff registerHealthStaff(HealthStaffPostDTO dto) {

        if(validateFields(dto) == 0){
            return null;
        }

        HealthStaff healthStaff = new HealthStaff();

        healthStaff.setUsername(dto.getUsername());
        healthStaff.setPassword(dto.getPassword());
        healthStaff.setRole(dto.getRole());
        healthStaff.setName(dto.getName());
        healthStaff.setDocument(dto.getDocument());
        healthStaff.setSpecialty(dto.getSpecialty());

        return healthStaffRepository.save(healthStaff);
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        HealthStaff toValidatehealthStaff = healthStaffRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (toValidatehealthStaff == null) return null;

        return new LoginResponseDTO("Login successful.",
                "exampletoken",
                toValidatehealthStaff.getId(),
                toValidatehealthStaff.getUsername(),
                toValidatehealthStaff.getName(),
                toValidatehealthStaff.getRole());
    }
}
