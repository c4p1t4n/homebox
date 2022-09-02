package school.sptech.server.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @ManyToOne
    private User user;

    public Msg(Integer idMsg, String message, Character automatic, User user) {
        this.idMsg = idMsg;
        this.message = message;
        this.automatic = automatic;
        this.user = user;
    }

    @JsonCreator
    public Msg(@JsonProperty("message") String message, User user) {
        this.message = message;
        this.automatic = 'n';
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
