package school.sptech.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "services_status_recent")
public class ServicesStatusRecent {

    @Column(name = "worker_id")
    private Integer workerId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Id
    @Column(name = "id_scheduling")
    private Integer idScheduling;

    @Column(name = "name_service")
    private String nameService;

    @Column(name = "service_description")
    private String serviceDescription;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private Double  price;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String  status;

    @Column(name = "deleted")
    private Character deleted;

    public ServicesStatusRecent() {
    }

    public ServicesStatusRecent(Integer workerId, Integer customerId, Integer idScheduling,
                                String nameService, String serviceDescription, String address,
                                Double price, LocalDate date, String status, Character deleted) {
        this.workerId = workerId;
        this.customerId = customerId;
        this.idScheduling = idScheduling;
        this.nameService = nameService;
        this.serviceDescription = serviceDescription;
        this.address = address;
        this.price = price;
        this.date = date;
        this.status = status;
        this.deleted = deleted;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getIdScheduling() {
        return idScheduling;
    }

    public void setIdScheduling(Integer idScheduling) {
        this.idScheduling = idScheduling;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Character getDeleted() {
        return deleted;
    }

    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }
}
