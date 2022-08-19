package school.sptech.server.response;

import java.time.LocalDateTime;

import school.sptech.server.model.Chat;
import school.sptech.server.model.Msg;
import school.sptech.server.model.User;

public class ChatsPerUser {
    private Integer id;

    private User user;

    private Chat chat;

    private Msg lastMessage;

    private LocalDateTime sendDate;

    private Character seen;

    public ChatsPerUser(Integer id, User user, Chat chat, Msg lastMessage, LocalDateTime sendDate, Character seen) {
        this.id = id;
        this.user = user;
        this.chat = chat;
        this.lastMessage = lastMessage;
        this.sendDate = sendDate;
        this.seen = seen;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public Character getSeen() {
        return seen;
    }

    public void setSeen(Character seen) {
        this.seen = seen;
    }

    public ChatsPerUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Msg getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Msg lastMessage) {
        this.lastMessage = lastMessage;
    }


}
