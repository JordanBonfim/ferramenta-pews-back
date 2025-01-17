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
    private String document;
    private String specialty;

    // Setter para 'name'
    public void setName(String name) {
        this.name = name;
    }

    // Setter para 'document'
    public void setDocument(String document) {
        this.document = document;
    }

    // Setter para 'specialty'
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getSpecialty() {
        return specialty;
    }
}
