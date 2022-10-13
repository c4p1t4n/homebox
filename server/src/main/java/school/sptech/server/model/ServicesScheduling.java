package school.sptech.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "services_scheduling")
public class ServicesScheduling implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "worker_id")
    private Integer workerId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "name_service")
    private String nameService;

    @Column(name = "service_description")
    private String serviceDescription;

    @Column(name = "address")
    private String address;

    @Column(name = "reference_price")
    private Double  referencePrice;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String  status;

    public ServicesScheduling() {
    }

    public ServicesScheduling(Integer id, Integer workerId, Integer customerId, String nameService,
                              String serviceDescription, String address, Double referencePrice,
                              LocalDate date, String status) {
        this.id = id;
        this.workerId = workerId;
        this.customerId = customerId;
        this.nameService = nameService;
        this.serviceDescription = serviceDescription;
        this.address = address;
        this.referencePrice = referencePrice;
        this.date = date;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Double referencePrice) {
        this.referencePrice = referencePrice;
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
}
