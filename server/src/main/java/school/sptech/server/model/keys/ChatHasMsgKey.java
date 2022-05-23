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
}
