package school.sptech.server.response;

import java.time.LocalDate;

public class NotificationJoinUserNotificationResponse {
    private Integer fkUser;
    private Integer idNotification;
    private String title;
    private String message;
    private LocalDate notificationDate;
    private Character seen;

    public NotificationJoinUserNotificationResponse() {
    }

    public NotificationJoinUserNotificationResponse(Integer fkUser,
            LocalDate notificationDate, Character seen, Integer idNotification, String title, String message) {
        this.fkUser = fkUser;
        this.idNotification = idNotification;
        this.title = title;
        this.message = message;
        this.notificationDate = notificationDate;
        this.seen = seen;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDate notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Character getSeen() {
        return seen;
    }

    public void setSeen(Character seen) {
        this.seen = seen;
    }

}
