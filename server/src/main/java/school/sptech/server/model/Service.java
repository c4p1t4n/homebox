package school.sptech.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idService;

    @ManyToOne
    private User worker;

    @ManyToOne
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "reference_price")
    private Double referencePrice;

    public Service() {
    }

    public Service(User worker, Category category, String name, String description, Double referencePrice) {
        this.worker = worker;
        this.category = category;
        this.name = name;
        this.description = description;
        this.referencePrice = referencePrice;
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public Service(Integer idService, User worker, Category category, String name, String description,
            Double referencePrice) {
        this.idService = idService;
        this.worker = worker;
        this.category = category;
        this.name = name;
        this.description = description;
        this.referencePrice = referencePrice;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(Double referencePrice) {
        this.referencePrice = referencePrice;
    }

}
