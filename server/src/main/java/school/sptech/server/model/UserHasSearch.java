package school.sptech.server.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "user_has_search")
public class UserHasSearch {
    @Id
    @Column(name = "id_user_has_search")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Search search;

    @ManyToOne
    private User user;

    @PastOrPresent
    @NotNull
    @Column(name = "search_date")
    private LocalDate searchDate;

    public UserHasSearch() {
    }

    public UserHasSearch(Search search, User user, @PastOrPresent @NotNull LocalDate searchDate) {
        this.search = search;
        this.user = user;
        this.searchDate = searchDate;
    }

    public UserHasSearch(Integer id, Search search, User user, @PastOrPresent @NotNull LocalDate searchDate) {
        this.id = id;
        this.search = search;
        this.user = user;
        this.searchDate = searchDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDate searchDate) {
        this.searchDate = searchDate;
    }
}
