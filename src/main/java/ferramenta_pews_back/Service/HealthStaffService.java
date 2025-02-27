package ferramenta_pews_back.Service;


import ferramenta_pews_back.DTOs.User.HealthStaffPutDTO;
import ferramenta_pews_back.DTOs.User.LoginRequestDTO;
import ferramenta_pews_back.DTOs.User.LoginResponseDTO;
import ferramenta_pews_back.DTOs.User.HealthStaffPostDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Entities.User;
import ferramenta_pews_back.Repositories.HealthStaffRepository;
import ferramenta_pews_back.Tools.HealthStaffMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HealthStaffService {
    @Autowired
    HealthStaffRepository healthStaffRepository;

    HealthStaffMapper healthStaffMapper;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public User findByIdOrThrowBadRequestException(UUID uuid) throws BadRequestException {
        return healthStaffRepository.findById(uuid).orElseThrow(() -> new BadRequestException("User not found."));
    }
    public HealthStaff getByUserName(String userName) throws BadRequestException {
        return healthStaffRepository.findByUsername(userName).orElseThrow(() -> new BadRequestException("User not found."));
    }

    public List<HealthStaff> getAll() {
        return healthStaffRepository.findAll();
    }
    public HealthStaff getByUUID(UUID uuid) throws BadRequestException {
        return  healthStaffRepository.findById(uuid).orElseThrow(() -> new BadRequestException("User not found"));
    }
    public HealthStaff updateHealthStaff(HealthStaffPutDTO dto) throws BadRequestException{

        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

        HealthStaff savedHealthStaff = (HealthStaff) findByIdOrThrowBadRequestException(dto.getUuid());


        HealthStaff healthStaff = healthStaffMapper.toEntity(dto, savedHealthStaff, localDateTime);

        return healthStaffRepository.save(healthStaff);

    }

    public HealthStaff registerHealthStaff(HealthStaffPostDTO dto) throws BadRequestException {
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

        // Verificar se o username já existe
        Optional<HealthStaff> existingUser = healthStaffRepository.findByUsername(dto.getUsername());

        if (existingUser.isPresent()) {
            throw new BadRequestException("Username already exists.");
        }

        HealthStaff healthStaff = healthStaffMapper.toEntity(dto, localDateTime);

        return healthStaffRepository.save(healthStaff);
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        HealthStaff toValidatehealthStaff = healthStaffRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (toValidatehealthStaff == null) return null;

        return new LoginResponseDTO("Login successful",
                "exampletoken",
                toValidatehealthStaff.getId(),
                toValidatehealthStaff.getUsername(),
                toValidatehealthStaff.getName(),
                toValidatehealthStaff.getRole());
    }

    public void deleteHealthStaff(UUID uuid) throws BadRequestException {
        healthStaffRepository.delete((HealthStaff) findByIdOrThrowBadRequestException(uuid));
    }
}
