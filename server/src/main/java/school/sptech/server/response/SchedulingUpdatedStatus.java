package school.sptech.server.response;

import java.time.LocalDate;

import school.sptech.server.model.Service;
import school.sptech.server.model.User;

public class SchedulingUpdatedStatus {
    private Integer idScheduling;
    private User customer;
    private Service service;
    private String status;
    private LocalDate status_date;

    public SchedulingUpdatedStatus() {
    }

    public SchedulingUpdatedStatus(Integer idScheduling, User customer, Service service, String status,
            LocalDate status_date) {
        this.idScheduling = idScheduling;
        this.customer = customer;
        this.service = service;
        this.status = status;
        this.status_date = status_date;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStatus_date() {
        return status_date;
    }

    public void setStatus_date(LocalDate status_date) {
        this.status_date = status_date;
    }
}
