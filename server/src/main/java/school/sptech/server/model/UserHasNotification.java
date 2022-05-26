package school.sptech.server.model;

import school.sptech.server.model.keys.UserHasNotificationKey;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;



@Entity
@Table(name = "user_has_notification")

public class UserHasNotification {

    @EmbeddedId
    UserHasNotificationKey id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name ="id_user")
    private User user;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name ="id_notification")
    private Notification notification;


    @PastOrPresent
    @Column(name = "notification_date")
    private LocalDate notificationDate;

    @Column(name = "seen")
    private char seen;

    public UserHasNotification(Integer fkUser, Integer fkNotification, LocalDate notificationDate,
            char seen) {
        this.id = new UserHasNotificationKey(fkNotification,fkUser);
        this.notificationDate = notificationDate;
        this.seen = seen;
    }


    public UserHasNotification(Integer idUsuario,Integer idNotificao) {
        this.id = new UserHasNotificationKey(idUsuario,idNotificao);
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

    public UserHasNotificationKey getId() {
        return id;
    }

    public void setId(UserHasNotificationKey id) {
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
