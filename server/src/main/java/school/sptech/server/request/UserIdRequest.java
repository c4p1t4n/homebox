package school.sptech.server.request;

public class UserIdRequest {
    private Integer id;

    public UserIdRequest() {
    }

    public UserIdRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
