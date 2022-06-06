package school.sptech.server.model;

import javax.persistence.*;

@Entity
@Table(name = "user_has_chat")

public class UserHasChat {
    @Id
    @Column(name = "id_user_has_chat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Chat chat;

    public UserHasChat(User user, Chat chat) {
        this.user = user;
        this.chat = chat;
    }

    public UserHasChat(Integer id, User user, Chat chat) {
        this.id = id;
        this.user = user;
        this.chat = chat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserHasChat(Integer idCustomer, Integer idChat) {

    }

    public UserHasChat() {
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

}
