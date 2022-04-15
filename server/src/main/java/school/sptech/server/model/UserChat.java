package school.sptech.server.model;

import org.springframework.data.annotation.Id;
import school.sptech.server.service.UserChatId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "user_chat")
@IdClass(UserChatId.class)
public class UserChat {
    @Id
    @Column(name = "fk_user")
    private Integer fkUser;

    @Id
    @Column(name = "fk_chat")
    private Integer fkChat;

    public UserChat(Integer fkUser, Integer fkChat) {
        this.fkUser = fkUser;
        this.fkChat = fkChat;
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
