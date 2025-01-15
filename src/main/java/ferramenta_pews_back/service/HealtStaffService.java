package ferramenta_pews_back.service;

import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Repositories.HealthStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealtStaffService {
    @Autowired
    HealthStaffRepository healthStaffRepository;

    public HealthStaff registerHealth(){
        HealthStaff healthStaff = new HealthStaff();

        healthStaff.setRole("HEALTH_STAFF"); // Função específica
        healthStaff.setPassword("securePassword123"); // Senha de teste
        healthStaff.setUsername("health.staff01"); // Nome de usuário único
        healthStaff.setName("Dr. John Doe"); // Nome completo
        healthStaff.setSpecialty("Cardiologist"); // Especialidade médica
        healthStaff.setDocumento("123456789"); // Documento de identificação

        System.out.println("Usuário salvo: ");

        return healthStaffRepository.save(healthStaff);

    }
}
