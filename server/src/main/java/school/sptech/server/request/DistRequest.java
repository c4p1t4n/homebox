package school.sptech.server.request;

public class DistRequest {
    private String cep1;
    private String cep2;

    public DistRequest() {
    }

    public DistRequest(String cep1, String cep2) {
        this.cep1 = cep1;
        this.cep2 = cep2;
    }

    public String getCep1() {
        return cep1;
    }

    public void setCep1(String cep1) {
        this.cep1 = cep1;
    }

    public String getCep2() {
        return cep2;
    }

    public void setCep2(String cep2) {
        this.cep2 = cep2;
    }

}
