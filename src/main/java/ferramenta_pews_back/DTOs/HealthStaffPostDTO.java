package ferramenta_pews_back.DTOs;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true) // Para herança no Lombok
@Getter
public class HealthStaffPostDTO extends BaseUserDTO {
    private String role;      // Exemplo: HEALTH_STAFF
    private String name;
    private String document;
    private String specialty;

    // Getter para role
    public String getRole() {
        return role;
    }

    // Setter para role
    public void setRole(String role) {
        this.role = role;
    }

    // Getter para name
    public String getName() {
        return name;
    }

    // Setter para name
    public void setName(String name) {
        this.name = name;
    }

    // Getter para document
    public String getDocument() {
        return document;
    }

    // Setter para document
    public void setDocument(String document) {
        this.document = document;
    }

    // Getter para specialty
    public String getSpecialty() {
        return specialty;
    }

    // Setter para specialty
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }


    public HealthStaffPostDTO(String username, String password, String role, String name, String document, String specialty) {
        super(username, password);
        this.role = role;
        this.name = name;
        this.document = document;
        this.specialty = specialty;
    }
}
