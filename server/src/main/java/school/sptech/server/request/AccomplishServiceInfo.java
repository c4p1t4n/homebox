package school.sptech.server.request;

import school.sptech.server.model.Scheduling;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class AccomplishServiceInfo {
    @ManyToOne
    private Scheduling scheduling;
    private Double price;
    private String description;
    @PastOrPresent
    @Column(name = "service_date")
    private LocalDate serviceDate;

    public AccomplishServiceInfo() {
    }

    public AccomplishServiceInfo(Double price, String description, LocalDate serviceDate) {
        this.price = price;
        this.description = description;
        this.serviceDate = serviceDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }
}
