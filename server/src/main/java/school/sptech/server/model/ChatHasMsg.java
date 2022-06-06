package school.sptech.server.model;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "chat_has_msg")

public class ChatHasMsg {

    @Id
    @Column(name = "id_chat_has_msg")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private Msg msg;

    @PastOrPresent
    @Column(name = "send_date")
    private LocalDate sendDate;

    @Column(name = "seen")
    private Character seen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    public ChatHasMsg(Chat chat, Msg msg, @PastOrPresent LocalDate sendDate, Character seen) {
        this.chat = chat;
        this.msg = msg;
        this.sendDate = sendDate;
        this.seen = seen;
    }

    public ChatHasMsg(Integer id, Chat chat, Msg msg, @PastOrPresent LocalDate sendDate, Character seen) {
        this.id = id;
        this.chat = chat;
        this.msg = msg;
        this.sendDate = sendDate;
        this.seen = seen;
    }

    public ChatHasMsg() {
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
