package school.sptech.server.model;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat")
    private Integer idChat;

    @PastOrPresent
    @Column(name = "opening_date")
    private LocalDate openingDate;

    public Chat(@PastOrPresent LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public Chat(Integer idChat, @PastOrPresent LocalDate openingDate) {
        this.idChat = idChat;
        this.openingDate = openingDate;
    }

    public Chat() {
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }
}
