package school.sptech.server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "avg_rating")
public class AvgRating implements Serializable {
    @Id
    private Integer id;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "worker_id")
    private Integer workerId;

    public AvgRating() {
    }

    public AvgRating(Integer rating, Integer workerId) {
        this.rating = rating;
        this.workerId = workerId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }
}
