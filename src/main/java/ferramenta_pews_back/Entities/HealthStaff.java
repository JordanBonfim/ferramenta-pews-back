package ferramenta_pews_back.Entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Getter
public class HealthStaff extends User {

    private String name;
    private String documento;
    private String specialty;

    // Setter para 'name'
    public void setName(String name) {
        this.name = name;
    }

    // Setter para 'documento'
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    // Setter para 'specialty'
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public String getDocumento() {
        return documento;
    }

    public String getSpecialty() {
        return specialty;
    }
}
