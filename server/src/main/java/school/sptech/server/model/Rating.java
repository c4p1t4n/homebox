package school.sptech.server.model;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rating")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "fk_accomplished_service")
    private AccomplishedService accomplishedService;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "description")
    private String description;

    public Rating() {
    }

    public Rating(AccomplishedService accomplishedService, Integer rating, String description) {
        this.accomplishedService = accomplishedService;
        this.rating = rating;
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public AccomplishedService getAccomplishedService() {
        return accomplishedService;
    }

    public void setAccomplishedService(AccomplishedService accomplishedService) {
        this.accomplishedService = accomplishedService;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
