package school.sptech.server.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@Entity
@Table(name = "msg")
public class Msg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_msg")
    private Integer idMsg;

    @Column(name = "message")
    private String message;

    @Column(name = "automatic")
    private Character automatic;

    @Column(name = "fk_user")
    private Integer fkUser;

    @OneToMany(mappedBy = "msg")
    Set<ChatHasMsg> chatHasMsg;

    public Msg(Integer idMsg, String message, Character automatic, Integer fkUser) {
        this.idMsg = idMsg;
        this.message = message;
        this.automatic = automatic;
        this.fkUser = fkUser;
    }

    @JsonCreator
    public Msg(@JsonProperty("message") String message, @JsonProperty("fkUser") Integer fkUser) {
        this.message = message;
        this.fkUser = fkUser;
    }

    public Msg() {
    }

    public Integer getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(Integer idMsg) {
        this.idMsg = idMsg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Character getAutomatic() {
        return automatic;
    }

    public void setAutomatic(Character automatic) {
        this.automatic = automatic;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }
}
