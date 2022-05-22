package school.sptech.server.id;

import java.io.Serializable;

public class ChatHasMsgId implements Serializable {
    private Integer fkMsg;
    private Integer fkChat;

    public ChatHasMsgId() {
    }

    public ChatHasMsgId(Integer fkMsg, Integer fkChat) {
        this.fkMsg = fkMsg;
        this.fkChat = fkChat;
    }

    public Integer getFkMsg() {
        return fkMsg;
    }

    public void setFkMsg(Integer fkMsg) {
        this.fkMsg = fkMsg;
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
        result = prime * result + ((fkMsg == null) ? 0 : fkMsg.hashCode());
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
        ChatHasMsgId other = (ChatHasMsgId) obj;
        if (fkChat == null) {
            if (other.fkChat != null)
                return false;
        } else if (!fkChat.equals(other.fkChat))
            return false;
        if (fkMsg == null) {
            if (other.fkMsg != null)
                return false;
        } else if (!fkMsg.equals(other.fkMsg))
            return false;
        return true;
    }
}
