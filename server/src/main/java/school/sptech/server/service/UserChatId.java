package school.sptech.server.service;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class UserChatId implements Serializable {
    private Integer fkUser;
    Integer fkChat;

    public UserChatId(Integer fkUser, Integer fkChat) {
        this.fkUser = fkUser;
        this.fkChat = fkChat;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getFkChat() {
        return fkChat;
    }

    public void setFkChat(Integer fkChat) {
        this.fkChat = fkChat;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fkChat == null) ? 0 : fkChat.hashCode());
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
        UserChatId other = (UserChatId) obj;
        if (fkChat == null) {
            if (other.fkChat != null)
                return false;
        } else if (!fkChat.equals(other.fkChat))
            return false;
        if (fkUser == null) {
            if (other.fkUser != null)
                return false;
        } else if (!fkUser.equals(other.fkUser))
            return false;
        return true;
    }
}
