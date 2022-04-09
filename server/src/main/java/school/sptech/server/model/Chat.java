package school.sptech.server.model;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_chat;

    @PastOrPresent
    @Column(name = "opening_date")
    private LocalDate openingDate;

    public Integer getId_chat() {
        return id_chat;
    }

    public void setId_chat(Integer id_chat) {
        this.id_chat = id_chat;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }
}
