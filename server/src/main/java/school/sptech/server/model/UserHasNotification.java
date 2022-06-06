package school.sptech.server.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "user_has_notification")
public class UserHasNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_has_notification")
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Notification notification;

    @PastOrPresent
    @Column(name = "notification_date")
    private LocalDate notificationDate;

    @Column(name = "seen")
    private char seen;

    public UserHasNotification(User user, Notification notification, @PastOrPresent LocalDate notificationDate,
            char seen) {
        this.user = user;
        this.notification = notification;
        this.notificationDate = notificationDate;
        this.seen = seen;
    }

    public UserHasNotification(Integer id, User user, Notification notification,
            @PastOrPresent LocalDate notificationDate, char seen) {
        this.id = id;
        this.user = user;
        this.notification = notification;
        this.notificationDate = notificationDate;
        this.seen = seen;
    }

    public UserHasNotification() {
    }

    public LocalDate getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDate notificationDate) {
        this.notificationDate = notificationDate;
    }

    public char getSeen() {
        return seen;
    }

    public void setSeen(char seen) {
        this.seen = seen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
