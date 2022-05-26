package school.sptech.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idScheduling;

    @ManyToOne
    private User customer;

    @ManyToOne
    private Service service;

    public Scheduling() {
    }

    public Scheduling(Integer idScheduling, User customer, Service service) {
        this.idScheduling = idScheduling;
        this.customer = customer;
        this.service = service;
    }

    public Scheduling(User customer, Service service) {
        this.customer = customer;
        this.service = service;
    }

    public Integer getIdScheduling() {
        return idScheduling;
    }

    public void setIdScheduling(Integer idScheduling) {
        this.idScheduling = idScheduling;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}
