package school.sptech.server.request;

public class UserSearchRequest {
    private String value;

    private Integer idUser;

    public UserSearchRequest(String value, Integer idUser) {
        this.value = value;
        this.idUser = idUser;
    }

    public UserSearchRequest() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
