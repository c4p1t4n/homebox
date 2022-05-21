package school.sptech.server.model;

import school.sptech.server.service.UserHasSearchId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "search_user")
@IdClass(UserHasSearchId.class)
public class SearchUser {
    @Id
    @Column(name = "fk_search")
    private Integer fkSearch;
    @Id
    @Column(name = "fk_user")
    private Integer fkUser;
    @PastOrPresent
    @NotNull
    @Column(name = "search_date")
    private LocalDate searchDate;

    public SearchUser(Integer fkSearch, Integer fkUser, LocalDate searchDate) {
        this.fkSearch = fkSearch;
        this.fkUser = fkUser;
        this.searchDate = searchDate;
    }

    public SearchUser() {
    }

    public Integer getFkSearch() {
        return fkSearch;
    }

    public void setFkSearch(Integer fkSearch) {
        this.fkSearch = fkSearch;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public LocalDate getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDate searchDate) {
        this.searchDate = searchDate;
    }
}
