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
}
