package school.sptech.server.model;

import org.springframework.data.annotation.Id;
import school.sptech.server.service.ChatHasMsgId;
import school.sptech.server.service.UserChatId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "chat_has_msg")
@IdClass(ChatHasMsgId.class)
public class ChatHasMsg {
    @Id
    @Column(name = "fk_msg")
    private Integer fkMsg;

    @Id
    @Column(name = "fk_chat")
    private Integer fkChat;

    @PastOrPresent
    @Column(name = "send_date")
    private LocalDate sendDate;

    private char seen;

    public ChatHasMsg(Integer fkMsg, Integer fkChat, LocalDate sendDate, char seen) {
        this.fkMsg = fkMsg;
        this.fkChat = fkChat;
        this.sendDate = sendDate;
        this.seen = seen;
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

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public char getRead() {
        return seen;
    }

    public void setRead(char read) {
        this.seen = seen;
    }
}
