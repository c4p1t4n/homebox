package school.sptech.server.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.server.model.Staff;
import school.sptech.server.repository.StaffRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {StaffController.class})
class StaffControllerTest {

    @Autowired
    StaffController staffController;

    @MockBean
    private StaffRepository dbRepositoryStaff;

    @Test
    @DisplayName("Sem staff deveria retornar 204 SEM corpo")
    void getStaffEmpty() {
        when(dbRepositoryStaff.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Staff>> response = staffController.getStaff();

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("Com staff deveria retornar 200 e COM corpo")
    void getStaffWithBody(){
        Staff staff1 = mock(Staff.class);
        Staff staff2 = mock(Staff.class);
        List<Staff> mockList = List.of(staff1, staff2);

        when(dbRepositoryStaff.findAll()).thenReturn(mockList);

        ResponseEntity<List<Staff>> response = staffController.getStaff();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockList, response.getBody());
    }

    @Test
    @DisplayName("No cadastro de um staff, se der certo, deve retornar 201 SEM corpo")
    void postStaffWithResult() {
        Staff staff1 = mock(Staff.class);
        when(dbRepositoryStaff.save(staff1)).thenReturn(new Staff());

        ResponseEntity<Object> response = staffController.cadastrarStaff(staff1);

        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

}