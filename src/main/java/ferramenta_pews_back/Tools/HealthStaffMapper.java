package ferramenta_pews_back.Tools;

import ferramenta_pews_back.DTOs.HealthStaffPostDTO;
import ferramenta_pews_back.DTOs.HealthStaffPutDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import java.time.LocalDateTime;
import java.util.UUID;

public class HealthStaffMapper {

    // Método para mapear de HealthStaffPostDTO para HealthStaff
    public static HealthStaff toEntity(HealthStaffPostDTO dto, LocalDateTime time) {
        HealthStaff healthStaff = new HealthStaff();
        healthStaff.setUsername(dto.getUsername());
        healthStaff.setPassword(dto.getPassword());
        healthStaff.setRole(dto.getRole());
        healthStaff.setName(dto.getName());
        healthStaff.setDocument(dto.getDocument());
        healthStaff.setSpecialty(dto.getSpecialty());
        healthStaff.setCreatedAt(time);
        healthStaff.setUpdatedAt(time);
        return healthStaff;
    }

    // Método para mapear de HealthStaffPutDTO para HealthStaff
    public static HealthStaff toEntity(HealthStaffPutDTO dto, HealthStaff savedHealthStaff,LocalDateTime updatedAt) {
        HealthStaff healthStaff = new HealthStaff();
        healthStaff.setId(savedHealthStaff.getId());
        healthStaff.setUsername(dto.getUsername());
        healthStaff.setPassword(dto.getPassword());
        healthStaff.setRole(dto.getRole());
        healthStaff.setName(dto.getName());
        healthStaff.setDocument(dto.getDocument());
        healthStaff.setSpecialty(dto.getSpecialty());
        healthStaff.setCreatedAt(savedHealthStaff.getCreatedAt());
        healthStaff.setUpdatedAt(updatedAt);
        return healthStaff;
    }
}
