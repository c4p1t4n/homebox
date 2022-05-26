package school.sptech.server.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Integer id;

    @Column(name = "title")
    private String title;
    @Column(name = "message")
    private String message;

    @OneToMany(mappedBy = "notification")
    Set<UserHasNotification> userHasNotifications;


    public Notification(Integer id, String title, String message) {
        this.id = id;
        this.title = title;
        this.message = message;
    }

    public Notification() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
