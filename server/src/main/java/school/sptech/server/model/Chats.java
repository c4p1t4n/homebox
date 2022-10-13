package school.sptech.server.model;

import javax.persistence.*;

@Entity
@Table(name = "chats")
public class Chats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_has_chat")
    private Integer id;

    @Column(name = "chat_id_chat")
    private Integer chatId;

    @Column(name = "user_id_user")
    private Integer userId;

    @Column(name = "id2")
    private Integer userId2;

    public Chats() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId2() {
        return userId2;
    }

    public void setUserId2(Integer userId2) {
        this.userId2 = userId2;
    }

    public Chats(Integer id, Integer chatId, Integer userId, Integer userId2) {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
        this.userId2 = userId2;
    }
}
