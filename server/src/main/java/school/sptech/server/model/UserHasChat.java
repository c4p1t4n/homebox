package school.sptech.server.model;

import school.sptech.server.service.UserChatId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "user_has_chat")
@IdClass(UserChatId.class)
public class UserHasChat {
    @Id
    @Column(name = "fk_user")
    private Integer fkUser;

    @Id
    @Column(name = "fk_chat")
    private Integer fkChat;

    public UserHasChat(Integer fkUser, Integer fkChat) {
        this.fkUser = fkUser;
        this.fkChat = fkChat;
    }

    public UserHasChat() {
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getFkChat() {
        return fkChat;
    }

    public void setFkChat(Integer fkChat) {
        this.fkChat = fkChat;
    }
}
