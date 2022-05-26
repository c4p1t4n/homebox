package school.sptech.server.model.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserHasNotificationKey implements Serializable {

    @Column(name="id_user")
    private Integer idUser;

    @Column(name="id_notification")
    private Integer idNotification;


    public UserHasNotificationKey( Integer idNotification, Integer idUser) {
        this.idUser = idUser;
        this.idNotification = idNotification;
    }
    public UserHasNotificationKey() {

    }
}
