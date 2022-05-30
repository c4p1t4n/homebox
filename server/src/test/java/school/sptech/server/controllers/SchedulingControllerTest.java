package school.sptech.server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import school.sptech.server.model.AccomplishedService;
import school.sptech.server.model.Category;
import school.sptech.server.model.Rating;
import school.sptech.server.model.Scheduling;
import school.sptech.server.model.SchedulingStatus;
import school.sptech.server.model.Service;
import school.sptech.server.model.User;
import school.sptech.server.repository.AccomplishedServiceRepository;
import school.sptech.server.repository.RatingRepository;
import school.sptech.server.repository.SchedulingRepository;
import school.sptech.server.repository.SchedulingStatusRepository;
import school.sptech.server.repository.ServiceRepository;
import school.sptech.server.repository.UserRepository;
import school.sptech.server.request.AccomplishServiceInfo;
import school.sptech.server.request.RatingCreationRequest;
import school.sptech.server.request.SchedulingCreationRequest;

@SpringBootTest(classes = { SchedulingController.class })
public class SchedulingControllerTest {

    @Autowired
    SchedulingController controller;

    @MockBean
    private ServiceRepository dbRepositoryService;
    @MockBean
    private UserRepository dbRepositoryUser;
    @MockBean
    private SchedulingRepository dbRepositoryScheduling;
    @MockBean
    private SchedulingStatusRepository dbRepositorySchedulingStatus;
    @MockBean
    private AccomplishedServiceRepository dbRepositoryAccomplishedService;
    @MockBean
    private RatingRepository dbRepositoryRating;

    @Test
    void testAccomplishSchedulingWhenAllInfoIsFine() {
        AccomplishServiceInfo requestMock = mock(AccomplishServiceInfo.class);
        Scheduling schedulingMock = mock(Scheduling.class);
        schedulingMock.setIdScheduling(1);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));
        when(dbRepositoryAccomplishedService.existsById(1)).thenReturn(false);

        ResponseEntity<Object> response = controller.accomplishScheduling(1, requestMock);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testAccomplishSchedulingWhenSchedulingDoesNotExist() {
        AccomplishServiceInfo requestMock = mock(AccomplishServiceInfo.class);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.ofNullable(null));

        ResponseEntity<Object> response = controller.accomplishScheduling(1, requestMock);

        assertEquals(404, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Agendamento inexistente", response.getBody());
    }

    @Test
    void testAccomplishSchedulingWhenTheSchedulingHasAlreadyBeenAccomplished() {
        AccomplishServiceInfo requestMock = mock(AccomplishServiceInfo.class);
        Scheduling schedulingMock = mock(Scheduling.class);
        schedulingMock.setIdScheduling(1);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));
        when(dbRepositoryAccomplishedService.existsBySchedulingIdScheduling(schedulingMock.getIdScheduling()))
                .thenReturn(true);

        ResponseEntity<Object> response = controller.accomplishScheduling(1, requestMock);

        assertEquals(400, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("O serviço relacionado a esse agendamento ja foi prestado", response.getBody());
    }

    @Test
    void testCreateWhenAllInfoIsFine() {
        SchedulingCreationRequest requestMock = new SchedulingCreationRequest(1, 1);
        User workerMock = mock(User.class);
        Category categoryMock = mock(Category.class);
        User customerMock = new User(1, "name", "email", "password", "cpf", "token", "customer", "picture", "cep");
        Service serviceMock = new Service(1, workerMock, categoryMock, "name", "description", 1000d);
        Scheduling schedulingMock = mock(Scheduling.class);
        SchedulingStatus schedulingStatusMock = mock(SchedulingStatus.class);
        when(dbRepositoryUser.findById(requestMock.getFkUser())).thenReturn(Optional.of(customerMock));
        when(dbRepositoryService.findById(requestMock.getFkService())).thenReturn(Optional.of(serviceMock));

        when(dbRepositoryScheduling.existsByCustomerIdAndServiceIdService(customerMock.getId(),
                serviceMock.getIdService())).thenReturn(false);

        when(dbRepositoryScheduling.save(schedulingMock)).thenReturn(schedulingMock);
        when(dbRepositorySchedulingStatus.save(schedulingStatusMock)).thenReturn(schedulingStatusMock);

        ResponseEntity<Object> response = controller.create(requestMock);

        assertEquals(201, response.getStatusCodeValue());

    }

    @Test
    void testCreateWhenTheServiceDoesNotExist() {
        SchedulingCreationRequest requestMock = new SchedulingCreationRequest(1, 4);
        User customerMock = new User(1, "name", "email", "password", "cpf", "token", "customer", "picture", "cep");

        when(dbRepositoryUser.findById(requestMock.getFkUser())).thenReturn(Optional.of(customerMock));
        when(dbRepositoryService.findById(requestMock.getFkService())).thenReturn(Optional.ofNullable(null));

        ResponseEntity<Object> response = controller.create(requestMock);

        assertEquals(404, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Serviço inexistente", response.getBody());

    }

    @Test
    void testCreateWhenTheCustomerDoesNotExist() {
        SchedulingCreationRequest requestMock = new SchedulingCreationRequest(4, 1);
        User workerMock = mock(User.class);
        Category categoryMock = mock(Category.class);

        Service serviceMock = new Service(1, workerMock, categoryMock, "name", "description", 1000d);
        when(dbRepositoryUser.findById(requestMock.getFkUser())).thenReturn(Optional.ofNullable(null));
        when(dbRepositoryService.findById(requestMock.getFkService())).thenReturn(Optional.of(serviceMock));

        ResponseEntity<Object> response = controller.create(requestMock);

        assertEquals(404, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Usuario inexistente", response.getBody());

    }

    @Test
    void testCreateWhenCustomerIsRegisteredAsWorker() {
        SchedulingCreationRequest requestMock = new SchedulingCreationRequest(1, 1);
        User workerMock = mock(User.class);
        Category categoryMock = mock(Category.class);
        User customerMock = new User(1, "name", "email", "password", "cpf", "token", "worker", "picture", "cep");
        Service serviceMock = new Service(1, workerMock, categoryMock, "name", "description", 1000d);

        when(dbRepositoryUser.findById(requestMock.getFkUser())).thenReturn(Optional.of(customerMock));
        when(dbRepositoryService.findById(requestMock.getFkService())).thenReturn(Optional.of(serviceMock));

        ResponseEntity<Object> response = controller.create(requestMock);

        assertEquals(400, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Usuário cadastrado como prestador de serviço", response.getBody());

    }

    @Test
    void testCreateWhenCustomerAlreadyHasThatSameServiceScheduled() {
        SchedulingCreationRequest requestMock = new SchedulingCreationRequest(1, 1);
        User workerMock = mock(User.class);
        Category categoryMock = mock(Category.class);
        User customerMock = new User(1, "name", "email", "password", "cpf", "token", "customer", "picture", "cep");
        Service serviceMock = new Service(1, workerMock, categoryMock, "name", "description", 1000d);

        when(dbRepositoryUser.findById(requestMock.getFkUser())).thenReturn(Optional.of(customerMock));
        when(dbRepositoryService.findById(requestMock.getFkService())).thenReturn(Optional.of(serviceMock));

        when(dbRepositoryScheduling.existsByCustomerIdAndServiceIdService(customerMock.getId(),
                serviceMock.getIdService())).thenReturn(true);

        ResponseEntity<Object> response = controller.create(requestMock);

        assertEquals(403, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Usuário já possui agendamento do serviço em questão", response.getBody());

    }

    @Test
    void testGetAllWhenListIsEmpty() {
        when(dbRepositoryScheduling.findAll()).thenReturn(new ArrayList<Scheduling>());
        ResponseEntity<List<Scheduling>> response = controller.getAll();

        assertEquals(204, response.getStatusCodeValue());

        assertNull(response.getBody());
    }

    @Test
    void testGetAllWhenListIsNotEmpty() {

        Scheduling mock1 = mock(Scheduling.class);
        Scheduling mock2 = mock(Scheduling.class);
        List<Scheduling> listaMock = List.of(mock1, mock2);

        when(dbRepositoryScheduling.findAll()).thenReturn(listaMock);

        ResponseEntity<List<Scheduling>> response = controller.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(listaMock, response.getBody());
    }

    @Test
    void testGetIdWhenTheIdIsInvalid() {

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.ofNullable(null));

        ResponseEntity<Scheduling> response = controller.getId(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testGetIdWhenTheIdIsValid() {

        Scheduling mock1 = mock(Scheduling.class);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(mock1));

        ResponseEntity<Scheduling> response = controller.getId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mock1, response.getBody());
    }

    @Test
    void testUpdateStatusWhenAllInfoIsFine() {
        Scheduling schedulingMock = mock(Scheduling.class);
        SchedulingStatus schedulingStatusMock = mock(SchedulingStatus.class);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));
        when(dbRepositorySchedulingStatus.save(schedulingStatusMock)).thenReturn(schedulingStatusMock);
        when(dbRepositorySchedulingStatus.existsByServiceStatusAndSchedulingIdScheduling("new status", 1))
                .thenReturn(false);

        ResponseEntity<Object> response = controller.updateStatus(1, "new status");

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testUpdateStatusWhenSchedulingDoesNotExist() {

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.ofNullable(null));

        ResponseEntity<Object> response = controller.updateStatus(1, "new status");

        assertEquals(404, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Agendamento inexistente", response.getBody());
    }

    @Test
    void testUpdateStatusWhenSchedulingAlreadyHasThatStatus() {
        Scheduling schedulingMock = mock(Scheduling.class);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));
        when(dbRepositorySchedulingStatus.existsByServiceStatusAndSchedulingIdScheduling("new status", 1))
                .thenReturn(true);

        ResponseEntity<Object> response = controller.updateStatus(1, "new status");

        assertEquals(400, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Agendamento ja possui o status: new status", response.getBody());
    }

    @Test
    void testSkipRatingSchedulingWhenAllInfoIsFine() {
        Scheduling schedulingMock = mock(Scheduling.class);
        SchedulingStatus schedulingStatusMock = mock(SchedulingStatus.class);
        schedulingMock.setIdScheduling(1);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));
        when(dbRepositoryAccomplishedService.existsBySchedulingIdScheduling(schedulingMock.getIdScheduling()))
                .thenReturn(true);
        when(dbRepositorySchedulingStatus.existsByServiceStatusAndSchedulingIdScheduling("rated",
                schedulingMock.getIdScheduling()))
                .thenReturn(true);
        when(dbRepositorySchedulingStatus
                .findFirstBySchedulingIdSchedulingOrderByStatusDateDesc(schedulingMock.getIdScheduling()))
                .thenReturn(schedulingStatusMock);
        when(dbRepositoryRating.existsByAccomplishedServiceSchedulingIdScheduling(schedulingMock.getIdScheduling()))
                .thenReturn(false);

        ResponseEntity<Object> response = controller.skipRatingScheduling(1);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(schedulingStatusMock, response.getBody());

    }

    @Test
    void testSkipRatingSchedulingWhenSchedulingHasNotBeenAccomplished() {
        Scheduling schedulingMock = mock(Scheduling.class);
        schedulingMock.setIdScheduling(1);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));
        when(dbRepositoryAccomplishedService.existsBySchedulingIdScheduling(schedulingMock.getIdScheduling()))
                .thenReturn(false);

        ResponseEntity<Object> response = controller.skipRatingScheduling(1);

        assertEquals(400, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("O serviço relacionado a esse agendamento ainda não foi prestado", response.getBody());

    }

    @Test
    void testSkipRatingSchedulingWhenSchedulingHasAlreadyBeenRated() {
        Scheduling schedulingMock = mock(Scheduling.class);
        schedulingMock.setIdScheduling(1);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));
        when(dbRepositoryAccomplishedService.existsBySchedulingIdScheduling(schedulingMock.getIdScheduling()))
                .thenReturn(true);
        when(dbRepositoryRating.existsByAccomplishedServiceSchedulingIdScheduling(schedulingMock.getIdScheduling()))
                .thenReturn(true);

        ResponseEntity<Object> response = controller.skipRatingScheduling(1);

        assertEquals(400, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("O serviço relacionado a esse agendamento já foi prestado e avaliado", response.getBody());

    }

    @Test
    void testSkipRatingSchedulingWhenSchedulingIsNotFound() {

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.ofNullable(null));

        ResponseEntity<Object> response = controller.skipRatingScheduling(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Agendamento inexistente", response.getBody());
    }

    @Test
    void testRateSchedulingWhenSchedulingHasNotBeenAccomplished() {
        RatingCreationRequest requestMock = mock(RatingCreationRequest.class);
        Scheduling schedulingMock = mock(Scheduling.class);
        schedulingMock.setIdScheduling(1);
        AccomplishedService accomplishedServiceMock = mock(AccomplishedService.class);

        when(dbRepositoryAccomplishedService.findBySchedulingIdScheduling(1))
                .thenReturn(Optional.of(accomplishedServiceMock));
        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));

        ResponseEntity<Object> response = controller.rateScheduling(1, requestMock);

        assertEquals(400, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("O serviço relacionado a esse agendamento ainda não foi prestado", response.getBody());
    }

    @Test
    void testRateSchedulingWhenSchedulingHasAlreadyBeenRated() {
        RatingCreationRequest requestMock = mock(RatingCreationRequest.class);
        Scheduling schedulingMock = mock(Scheduling.class);
        schedulingMock.setIdScheduling(1);
        AccomplishedService accomplishedServiceMock = mock(AccomplishedService.class);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));
        when(dbRepositoryRating.existsByAccomplishedServiceSchedulingIdScheduling(schedulingMock.getIdScheduling()))
                .thenReturn(true);
        when(dbRepositoryAccomplishedService.findBySchedulingIdScheduling(schedulingMock.getIdScheduling()))
                .thenReturn(Optional.of(accomplishedServiceMock));

        ResponseEntity<Object> response = controller.rateScheduling(1, requestMock);

        assertEquals(400, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("O serviço relacionado a esse agendamento já foi prestado e avaliado", response.getBody());
    }

    @Test
    void testRateSchedulingWhenAllInfoIsFine() {
        RatingCreationRequest requestMock = mock(RatingCreationRequest.class);
        Scheduling schedulingMock = mock(Scheduling.class);
        schedulingMock.setIdScheduling(1);
        AccomplishedService accomplishedServiceMock = mock(AccomplishedService.class);
        Rating ratingMock = mock(Rating.class);
        ratingMock.setAccomplishedService(accomplishedServiceMock);
        ratingMock.setDescription(requestMock.getDescription());
        ratingMock.setRating(requestMock.getRating());

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.of(schedulingMock));
        when(dbRepositoryAccomplishedService.findBySchedulingIdScheduling(schedulingMock.getIdScheduling()))
                .thenReturn(Optional.of(accomplishedServiceMock));
        when(dbRepositorySchedulingStatus.existsByServiceStatusAndSchedulingIdScheduling("rated",
                schedulingMock.getIdScheduling())).thenReturn(false);
        when(dbRepositoryRating.save(ratingMock)).thenReturn(ratingMock);

        ResponseEntity<Object> response = controller.rateScheduling(1, requestMock);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testRateSchedulingWhenSchedulingIsNotFound() {
        RatingCreationRequest requestMock = mock(RatingCreationRequest.class);

        when(dbRepositoryScheduling.findById(1)).thenReturn(Optional.ofNullable(null));

        ResponseEntity<Object> response = controller.rateScheduling(1, requestMock);

        assertEquals(404, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Agendamento inexistente", response.getBody());
    }
}
