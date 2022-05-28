package school.sptech.server.model.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ChatHasMsgKey implements Serializable {

    private static final long serialVersionUID = 8341404537701619415L;
    @Column(name="id_chat")
    private Integer idChat;

    @Column(name="id_msg")
    private Integer idMsg;

    public ChatHasMsgKey(Integer idChat, Integer idMsg) {
        this.idChat = idChat;
        this.idMsg = idMsg;
    }
    ChatHasMsgKey(){

    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idChat == null) ? 0 : idChat.hashCode());
        result = prime * result + ((idMsg == null) ? 0 : idMsg.hashCode());
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
        ChatHasMsgKey other = (ChatHasMsgKey) obj;
        if (idChat == null) {
            if (other.idChat != null)
                return false;
        } else if (!idChat.equals(other.idChat))
            return false;
        if (idMsg == null) {
            if (other.idMsg != null)
                return false;
        } else if (!idMsg.equals(other.idMsg))
            return false;
        return true;
    }
}
