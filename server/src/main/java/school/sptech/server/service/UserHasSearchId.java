package school.sptech.server.service;

import javax.persistence.Column;
import java.io.Serializable;

public class UserHasSearchId implements Serializable {
    @Column(name = "fk_user")
    private Integer fkUser;
    @Column(name = "fk_search")
    private Integer fkSearch;

    public UserHasSearchId() {
    }

    public UserHasSearchId(Integer fkUser, Integer fkSearch) {
        this.fkUser = fkUser;
        this.fkSearch = fkSearch;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getFkSearch() {
        return fkSearch;
    }

    public void setFkSearch(Integer fkSearch) {
        this.fkSearch = fkSearch;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fkSearch == null) ? 0 : fkSearch.hashCode());
        result = prime * result + ((fkUser == null) ? 0 : fkUser.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserHasSearchId other = (UserHasSearchId) obj;
        if (fkSearch == null) {
            if (other.fkSearch != null)
                return false;
        } else if (!fkSearch.equals(other.fkSearch))
            return false;
        if (fkUser == null) {
            if (other.fkUser != null)
                return false;
        } else if (!fkUser.equals(other.fkUser))
            return false;
        return true;
    }

}
