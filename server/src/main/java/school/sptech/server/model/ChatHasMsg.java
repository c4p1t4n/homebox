package school.sptech.server.model;

import school.sptech.server.model.keys.ChatHasMsgKey;


import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "chat_has_msg")

public class ChatHasMsg {

    @EmbeddedId
    ChatHasMsgKey id;

    @ManyToOne
    @MapsId("idChat")
    @JoinColumn(name ="id_chat")
    Chat chat;

    @ManyToOne
    @MapsId("idMsg")
    @JoinColumn(name = "id_msg")
    Msg msg;

    @PastOrPresent
    @Column(name = "send_date")
    private LocalDate sendDate;

    @Column(name = "seen")
    private Character seen;



    public ChatHasMsg() {
    }

    public ChatHasMsg(ChatHasMsgKey id, Chat chat, Msg msg, LocalDate sendDate, Character seen) {
        this.id = id;
        this.chat = chat;
        this.msg = msg;
        this.sendDate = sendDate;
        this.seen = seen;
    }

    public ChatHasMsg(Integer idMsg, Integer idChat, LocalDate now, char n) {

    }

    public ChatHasMsg(Integer fkMsg, Integer fkChat) {
        this.id = new ChatHasMsgKey(fkChat,fkMsg);
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public Character getSeen() {
        return seen;
    }

    public void setSeen(Character seen) {
        this.seen = seen;
    }
}
