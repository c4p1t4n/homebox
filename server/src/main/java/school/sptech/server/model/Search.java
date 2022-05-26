package school.sptech.server.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "search")
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_search")
    private Integer idSearch;

    @Column(name="value")
    @NotNull
    private String value;

    public Search(String value) {
        this.value = value;
    }

    public Search() {
    }

    public Integer getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(Integer idSearch) {
        this.idSearch = idSearch;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
