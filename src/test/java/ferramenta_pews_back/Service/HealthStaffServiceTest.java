package ferramenta_pews_back.Service;

import ferramenta_pews_back.DTOs.User.HealthStaffPostDTO;
import ferramenta_pews_back.DTOs.User.HealthStaffPutDTO;
import ferramenta_pews_back.DTOs.User.LoginRequestDTO;
import ferramenta_pews_back.DTOs.User.LoginResponseDTO;
import ferramenta_pews_back.Entities.HealthStaff;
import ferramenta_pews_back.Entities.User;
import ferramenta_pews_back.Repositories.HealthStaffRepository;
import ferramenta_pews_back.Tools.HealthStaffMapper;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HealthStaffServiceTest {

    @Mock
    private HealthStaffRepository healthStaffRepository;

    @Mock
    private HealthStaffMapper healthStaffMapper;

    @InjectMocks
    private HealthStaffService healthStaffService;

    private UUID healthStaffUuid;
    private HealthStaff mockHealthStaff;
    private HealthStaff mockRegisterHealthStaff;
    private HealthStaffPutDTO healthStaffPutDTO;
    private HealthStaffPostDTO healthStaffPostDTO;
    private LoginRequestDTO loginRequestDTO;
    private LoginResponseDTO loginResponseDTO;

    @BeforeEach
    void setUp() {
        healthStaffUuid = UUID.randomUUID();

        // Configuração do mock de HealthStaff
        mockHealthStaff = new HealthStaff();
        mockHealthStaff.setId(healthStaffUuid);
        mockHealthStaff.setUsername("john_doe"); // Corrigido para refletir o nome esperado no teste
        mockHealthStaff.setPassword("hashed_password");
        mockHealthStaff.setRole("HEALTH_STAFF");
        mockHealthStaff.setName("John Doe"); // Nome corrigido para "John Doe"
        mockHealthStaff.setDocument("123456789");
        mockHealthStaff.setSpecialty("Cardiology");

        // DTOs para os testes
        healthStaffPutDTO = new HealthStaffPutDTO(healthStaffUuid, "HEALTH_STAFF", "John Doe", "123456789", "Cardiology");

        // DTO para login
        loginRequestDTO = new LoginRequestDTO("john_doe", "hashed_password");
        loginResponseDTO = new LoginResponseDTO("Login successful", "exampleToken", healthStaffUuid, "john_doe", "John Doe", "HEALTH_STAFF");






        // Mock de HealthStaff para o novo usuário
        mockRegisterHealthStaff = new HealthStaff();
        mockRegisterHealthStaff.setId(UUID.randomUUID());
        mockRegisterHealthStaff.setUsername("new_user");
        mockRegisterHealthStaff.setPassword("hashed_password");
        mockRegisterHealthStaff.setRole("HEALTH_STAFF");
        mockRegisterHealthStaff.setName("Jane Doe");
        mockRegisterHealthStaff.setDocument("987654321");
        mockRegisterHealthStaff.setSpecialty("Neurology");
    }




    @Test
    void testFindByIdOrThrowBadRequestException_Success() throws BadRequestException {
        when(healthStaffRepository.findById(healthStaffUuid)).thenReturn(Optional.of(mockHealthStaff));

        User result = healthStaffService.findByIdOrThrowBadRequestException(healthStaffUuid);

        assertNotNull(result);
        assertEquals(mockHealthStaff.getId(), result.getId());
    }

    @Test
    void testFindByIdOrThrowBadRequestException_UserNotFound() {
        when(healthStaffRepository.findById(healthStaffUuid)).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> {
            healthStaffService.findByIdOrThrowBadRequestException(healthStaffUuid);
        });
    }

    @Test
    void testGetByUserName_Success() throws BadRequestException {
        when(healthStaffRepository.findByUsername("john_doe")).thenReturn(Optional.of(mockHealthStaff));

        HealthStaff result = healthStaffService.getByUserName("john_doe");

        assertNotNull(result);
    }

    @Test
    void testGetByUserName_UserNotFound() {
        when(healthStaffRepository.findByUsername("john_doe")).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> {
            healthStaffService.getByUserName("john_doe");
        });
    }




    @Test
    void testLogin_Success() {
        LoginRequestDTO dtoLogin = new LoginRequestDTO("john_doe", "hashed_password");
        when(healthStaffRepository.findByUsernameAndPassword("john_doe", "hashed_password")).thenReturn(mockHealthStaff);

        when(healthStaffRepository.findByUsernameAndPassword("john_doe", "hashed_password")).thenReturn(mockHealthStaff);

        LoginResponseDTO result = healthStaffService.login(dtoLogin);
        assertNotNull(result);
        assertEquals("Login successful", result.getMessage());
        assertEquals("exampletoken", result.getToken());
        assertEquals(healthStaffUuid, result.getUuid());
        assertEquals("john_doe", result.getUsername());
        assertEquals("John Doe", result.getName());
        assertEquals("HEALTH_STAFF", result.getRole());
    }

    @Test
    void testLogin_Failure() {
        when(healthStaffRepository.findByUsernameAndPassword("john_doe", "wrong_password")).thenReturn(null);
        loginRequestDTO = new LoginRequestDTO("john_doe", "wrong_password");

        LoginResponseDTO result = healthStaffService.login(loginRequestDTO);

        assertNull(result);
    }



    @Test
    void testDeleteHealthStaff_Success() throws BadRequestException {
        when(healthStaffRepository.findById(healthStaffUuid)).thenReturn(Optional.of(mockHealthStaff));

        healthStaffService.deleteHealthStaff(healthStaffUuid);

        verify(healthStaffRepository, times(1)).delete(mockHealthStaff);
    }

    @Test
    void testDeleteHealthStaff_UserNotFound() {
        when(healthStaffRepository.findById(healthStaffUuid)).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> {
            healthStaffService.deleteHealthStaff(healthStaffUuid);
        });
    }



    @Test
    void testRegisterHealthStaff_Success() throws BadRequestException {
        healthStaffPostDTO = new HealthStaffPostDTO(
                "HEALTH_STAFF",    // role
                "Jane Doe",        // name
                "987654321",       // document
                "Neurology"        // specialty
        );
        healthStaffPostDTO.setUsername("new_user");
        healthStaffPostDTO.setPassword("hashed_password");




        // Arrange
        when(healthStaffRepository.findByUsername("new_user"))
                .thenReturn(Optional.empty());




        when(healthStaffRepository.save(mockRegisterHealthStaff))
                .thenReturn(mockRegisterHealthStaff);

        // Act
        HealthStaff result = healthStaffService.registerHealthStaff(healthStaffPostDTO);
        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals("Neurology", result.getSpecialty());
        assertEquals("HEALTH_STAFF", result.getRole());
    }

}