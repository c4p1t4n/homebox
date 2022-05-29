package school.sptech.server.model.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserHasChatKey implements Serializable {
    @Column(name="id_chat")
    private Integer idChat;

    @Column(name="id_user")
    private Integer idUser;

    public UserHasChatKey(Integer idChat, Integer idUser) {
        this.idChat = idChat;
        this.idUser = idUser;
    }

    public UserHasChatKey() {

    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idChat == null) ? 0 : idChat.hashCode());
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
        UserHasChatKey other = (UserHasChatKey) obj;
        if (idChat == null) {
            if (other.idChat != null)
                return false;
        } else if (!idChat.equals(other.idChat))
            return false;
        if (idUser == null) {
            if (other.idUser != null)
                return false;
        } else if (!idUser.equals(other.idUser))
            return false;
        return true;
    }
}
