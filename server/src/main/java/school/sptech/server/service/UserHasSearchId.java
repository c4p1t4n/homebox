package school.sptech.server.service;

import java.io.Serializable;

public class UserHasSearchId implements Serializable {
    private Integer fkUser;
    private Integer fkNotification;

    public UserHasSearchId() {
    }

    public UserHasSearchId(Integer fkUser, Integer fkNotification) {
        this.fkUser = fkUser;
        this.fkNotification = fkNotification;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fkNotification == null) ? 0 : fkNotification.hashCode());
        result = prime * result + ((fkUser == null) ? 0 : fkUser.hashCode());
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
        UserHasSearchId other = (UserHasSearchId) obj;
        if (fkNotification == null) {
            if (other.fkNotification != null)
                return false;
        } else if (!fkNotification.equals(other.fkNotification))
            return false;
        if (fkUser == null) {
            if (other.fkUser != null)
                return false;
        } else if (!fkUser.equals(other.fkUser))
            return false;
        return true;
    }

}
