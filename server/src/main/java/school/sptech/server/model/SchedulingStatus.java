package school.sptech.server.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scheduling_status")
public class SchedulingStatus {
    @Id
    @Column(name = "id_scheduling_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSchedulingStatus;

    @Column(name = "service_status")
    private String serviceStatus;

    @Column(name = "status_date")
    private LocalDate statusDate;

    @ManyToOne
    private Scheduling scheduling;

    public SchedulingStatus() {
    }

    public SchedulingStatus(Integer idSchedulingStatus, String serviceStatus, LocalDate statusDate,
            Scheduling scheduling) {
        this.idSchedulingStatus = idSchedulingStatus;
        this.serviceStatus = serviceStatus;
        this.statusDate = statusDate;
        this.scheduling = scheduling;
    }

    public SchedulingStatus(String serviceStatus, LocalDate statusDate,
            Scheduling scheduling) {
        this.serviceStatus = serviceStatus;
        this.statusDate = statusDate;
        this.scheduling = scheduling;
    }

    public Integer getIdSchedulingStatus() {
        return idSchedulingStatus;
    }

    public void setIdSchedulingStatus(Integer idSchedulingStatus) {
        this.idSchedulingStatus = idSchedulingStatus;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public LocalDate getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }

    public Scheduling getScheduling() {
        return scheduling;
    }

    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }

}
