package school.sptech.server.model;

import javax.persistence.*;

@Entity
@Table(name = "service_has_tag")
public class ServiceHasTag {
    @Id
    @Column(name = "id_service_has_tag")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Service service;

    @ManyToOne
    private Tag tag;

    public ServiceHasTag() {
    }

    public ServiceHasTag(Service service, Tag tag) {
        this.service = service;
        this.tag = tag;
    }

    public ServiceHasTag(Integer id, Service service, Tag tag) {
        this.id = id;
        this.service = service;
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
