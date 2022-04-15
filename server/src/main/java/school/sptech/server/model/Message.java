package school.sptech.server.model;


import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Table(name = "msg")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_msg")
    private Integer idMsg;

    private String message;

    private char automatic;

    @Column(name = "fk_user")
    private int fkUser;

    public Message(Integer idMsg, String message, char automatic, int fkUser) {
        this.idMsg = idMsg;
        this.message = message;
        this.automatic = automatic;
        this.fkUser = fkUser;
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

    public char getAutomatic() {
        return automatic;
    }

    public void setAutomatic(char automatic) {
        this.automatic = automatic;
    }

    public int getFkUser() {
        return fkUser;
    }

    public void setFkUser(int fkUser) {
        this.fkUser = fkUser;
    }
}
