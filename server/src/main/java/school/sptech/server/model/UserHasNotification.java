package school.sptech.server.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;

import school.sptech.server.id.UserHasNotificationId;

@Entity
@Table(name = "user_has_notification")
@IdClass(UserHasNotificationId.class)
public class UserHasNotification {
    @Id
    @Column(name = "fk_notification")
    private Integer fkNotification;

    @Id
    @Column(name = "fk_user")
    private Integer fkUser;

    @PastOrPresent
    @Column(name = "notification_date")
    private LocalDate notificationDate;

    @Column(name = "seen")
    private char seen;

    public UserHasNotification(Integer fkUser, Integer fkNotification, LocalDate notificationDate,
            char seen) {
        this.fkUser = fkUser;
        this.fkNotification = fkNotification;
        this.notificationDate = notificationDate;
        this.seen = seen;
    }

    public UserHasNotification() {
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getFkNotification() {
        return fkNotification;
    }

    public void setFkNotification(Integer fkNotification) {
        this.fkNotification = fkNotification;
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

}
