package school.sptech.server.model;

import school.sptech.server.model.keys.UserHasChatKey;
import school.sptech.server.service.UserChatId;

import javax.persistence.*;

@Entity
@Table(name = "user_has_chat")

public class UserHasChat {

    @EmbeddedId
    private UserHasChatKey userHasChatKey;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name ="id_user")
    private User user;

    @ManyToOne
    @MapsId("idChat")
    @JoinColumn(name ="id_chat")
    private Chat chat;

    public UserHasChat(Integer idCustomer, Integer idChat) {

    }

    public UserHasChatKey getUserHasChatKey() {
        return userHasChatKey;
    }
    public UserHasChat() {
    }


    public void setUserHasChatKey(UserHasChatKey userHasChatKey) {
        this.userHasChatKey = userHasChatKey;
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
