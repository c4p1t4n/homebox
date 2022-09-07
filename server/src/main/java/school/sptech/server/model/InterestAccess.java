package school.sptech.server.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PastOrPresent;

@Entity
public class InterestAccess {
    @Id
    @Column(name = "id_interest_access")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tag tag;

    @PastOrPresent
    @Column(name = "access_date")
    private LocalDate accessDate;


    public InterestAccess(Integer id, User user, Tag tag, LocalDate accessDate) {
        this.id = id;
        this.user = user;
        this.tag = tag;
        this.accessDate = accessDate;
    }

    public InterestAccess() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }


    public LocalDate getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(LocalDate accessDate) {
        this.accessDate = accessDate;
    }
}
