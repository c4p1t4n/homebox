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

import school.sptech.server.model.Notification;
import school.sptech.server.repository.NotificationRepository;
import school.sptech.server.repository.UserHasNotificationRepository;
import school.sptech.server.service.UserService;

@SpringBootTest(classes = { NotificationController.class })
public class NotificationControllerTest {

    @Autowired
    private NotificationController controller;

    @MockBean
    private UserService dbUserService;

    @MockBean
    protected NotificationRepository dbRepositoryNotification;

    @MockBean
    protected UserHasNotificationRepository dbRepositoryUserHasNotification;

    @Test
    void testCreateNotification() {

        Notification mock1 = mock(Notification.class);
        mock1.setId(1);

        when(dbRepositoryNotification.save(mock1)).thenReturn(mock1);

        ResponseEntity<Notification> response = controller.createNotification(mock1);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mock1, response.getBody());
    }

    @Test
    void testGetNotificationWhenTheIdIsInvalid() {

        when(dbRepositoryNotification.existsById(1)).thenReturn(false);

        ResponseEntity<Notification> response = controller.getNotification(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testGetNotificationWhenTheIdIsValid() {

        Notification mock1 = mock(Notification.class);

        when(dbRepositoryNotification.existsById(1)).thenReturn(true);
        when(dbRepositoryNotification.findById(1)).thenReturn(Optional.of(mock1));

        ResponseEntity<Notification> response = controller.getNotification(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mock1, response.getBody());
    }

    @Test
    void testGetNotificationForUser() {

    }

    @Test
    void testGetNotificationsWhenListIsEmpty() {
        when(dbRepositoryNotification.findAll()).thenReturn(new ArrayList<Notification>());
        ResponseEntity<List<Notification>> response = controller.getNotifications();

        assertEquals(204, response.getStatusCodeValue());

        assertNull(response.getBody());
    }

    @Test
    void testGetNotificationsWhenListIsNotEmpty() {

        Notification mock1 = mock(Notification.class);
        Notification mock2 = mock(Notification.class);
        List<Notification> listaMock = List.of(mock1, mock2);

        when(dbRepositoryNotification.findAll()).thenReturn(listaMock);

        ResponseEntity<List<Notification>> response = controller.getNotifications();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(listaMock, response.getBody());
    }

    @Test
    void testGetNotificationsForUser() {

    }

    @Test
    void testReadNotification() {

    }

    @Test
    void testAssociateNotificationToUsers() {

    }

}
