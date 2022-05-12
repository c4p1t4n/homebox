package school.sptech.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.server.model.Notification;
import school.sptech.server.model.UserHasNotification;
import school.sptech.server.repository.NotificationRepository;

import school.sptech.server.repository.UserHasNotificationRepository;
import school.sptech.server.request.UserIdListRequest;
import school.sptech.server.response.NotificationJoinUserNotificationResponse;
import school.sptech.server.service.UserHasNotificationId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.service.UserService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    // está sendo utilizada a repository do user customer por ser necessário apenas
    // um pela tabela do banco de dados ser a mesma
    @Autowired
    private UserService dbUserService;

    @Autowired
    protected NotificationRepository dbRepositoryNotification;

    @Autowired
    protected UserHasNotificationRepository dbRepositoryUserHasNotification;

    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications() {
        List<Notification> notificationList = dbRepositoryNotification.findAll();

        if (notificationList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(notificationList);
    }

    @GetMapping(value = "/user/{idUser}")
    public ResponseEntity<List<NotificationJoinUserNotificationResponse>> getNotificationsForUser(
            @PathVariable Integer idUser) {
        if (!dbUserService.existsById(idUser)) {
            return ResponseEntity.status(404).build();
        }

        List<NotificationJoinUserNotificationResponse> list = dbRepositoryNotification
                .notificationsPerUserList(idUser);

        if (list.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(list);
    }

    @GetMapping(value = "/{idNotification}")
    public ResponseEntity<Notification> getNotification(@PathVariable Integer idNotification) {

        Optional<Notification> notification = dbRepositoryNotification.findById(idNotification);

        if (notification.isPresent()) {
            return ResponseEntity.status(200).body(notification.get());
        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping(value = "/user/{idUser}/{idNotification}")
    public ResponseEntity<NotificationJoinUserNotificationResponse> getNotificationForUser(
            @PathVariable Integer idNotification,
            @PathVariable Integer idUser) {
        List<NotificationJoinUserNotificationResponse> list = dbRepositoryNotification
                .getNotificationPerUser(idNotification, idUser);

        if (list.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(list.get(0));
    }

    @PostMapping
    public ResponseEntity createNotification(@RequestBody Notification notification) {

        dbRepositoryNotification.save(notification);
        return ResponseEntity.status(201).build();
    }

    @PostMapping(value = "/{idNotification}")
    public ResponseEntity associateNotificationToUsers(@PathVariable Integer idNotification,
            @RequestBody UserIdListRequest idList) {
        if (dbRepositoryNotification.existsById(idNotification)) {
            for (Integer id : idList.getUserIds()) {
                if (dbUserService.existsById(id)) {
                    UserHasNotification userHasNotification = new UserHasNotification(id, idNotification,
                            LocalDate.now(), 'n');
                    dbRepositoryUserHasNotification.save(userHasNotification);
                }

            }

            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PatchMapping(value = "/read/{fkUser}/{fkNotification}")
    public ResponseEntity readNotification(@PathVariable Integer fkUser, @PathVariable Integer fkNotification) {
        if (dbRepositoryUserHasNotification.existsById(new UserHasNotificationId(fkUser, fkNotification))) {
            dbRepositoryUserHasNotification.readNotification(fkNotification, fkUser);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

}
