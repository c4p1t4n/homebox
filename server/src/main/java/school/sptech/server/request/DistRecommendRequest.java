package school.sptech.server.request;

public class DistRecommendRequest {
    private Integer user_id;
    private Integer qtd;

    public DistRecommendRequest() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public DistRecommendRequest(Integer user_id, Integer qtd) {
        this.user_id = user_id;
        this.qtd = qtd;
    }

}
