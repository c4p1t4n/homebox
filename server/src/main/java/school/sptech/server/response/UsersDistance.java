package school.sptech.server.response;

public class UsersDistance {
    private Integer id_worker;
    private String worker_name;
    private String worker_cep;
    private Integer id_customer;
    private String customer_name;
    private String customer_cep;
    private Double distance;

    public Integer getId_worker() {
        return id_worker;
    }

    public void setId_worker(Integer id_worker) {
        this.id_worker = id_worker;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getWorker_cep() {
        return worker_cep;
    }

    public void setWorker_cep(String worker_cep) {
        this.worker_cep = worker_cep;
    }

    public Integer getId_customer() {
        return id_customer;
    }

    public void setId_customer(Integer id_customer) {
        this.id_customer = id_customer;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_cep() {
        return customer_cep;
    }

    public void setCustomer_cep(String customer_cep) {
        this.customer_cep = customer_cep;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public UsersDistance() {
    }

    public UsersDistance(Integer id_worker, String worker_name, String worker_cep, Integer id_customer,
            String customer_name, String customer_cep, Double distance) {
        this.id_worker = id_worker;
        this.worker_name = worker_name;
        this.worker_cep = worker_cep;
        this.id_customer = id_customer;
        this.customer_name = customer_name;
        this.customer_cep = customer_cep;
        this.distance = distance;
    }
}
