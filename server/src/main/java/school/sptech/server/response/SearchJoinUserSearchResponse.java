package school.sptech.server.response;

import java.time.LocalDate;

public class SearchJoinUserSearchResponse {
    private Integer fkUser;

    private Integer idSearch;
    private String message;

    private LocalDate SearchDate;
    

    public SearchJoinUserSearchResponse() {
    }

    public SearchJoinUserSearchResponse(Integer fkUser, Integer idSearch, String message, LocalDate searchDate) {
        this.fkUser = fkUser;
        this.idSearch = idSearch;
        this.message = message;
        SearchDate = searchDate;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(Integer idSearch) {
        this.idSearch = idSearch;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getSearchDate() {
        return SearchDate;
    }

    public void setSearchDate(LocalDate searchDate) {
        SearchDate = searchDate;
    }
}
