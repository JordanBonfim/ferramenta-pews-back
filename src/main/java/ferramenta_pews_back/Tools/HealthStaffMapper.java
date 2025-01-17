package ferramenta_pews_back.Tools;

import ferramenta_pews_back.DTOs.HealthStaffPostDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthStaffMapper {
    HealthStaff toEntity(HealthStaffPostDTO dto);
}
