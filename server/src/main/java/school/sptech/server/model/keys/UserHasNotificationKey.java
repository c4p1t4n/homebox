package school.sptech.server.model.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserHasNotificationKey implements Serializable {

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "id_notification")
    private Integer idNotification;

    public UserHasNotificationKey(Integer idNotification, Integer idUser) {
        this.idUser = idUser;
        this.idNotification = idNotification;
    }

    public UserHasNotificationKey() {

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idNotification == null) ? 0 : idNotification.hashCode());
        result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserHasNotificationKey other = (UserHasNotificationKey) obj;
        if (idNotification == null) {
            if (other.idNotification != null)
                return false;
        } else if (!idNotification.equals(other.idNotification))
            return false;
        if (idUser == null) {
            if (other.idUser != null)
                return false;
        } else if (!idUser.equals(other.idUser))
            return false;
        return true;
    }
}
