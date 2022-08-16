package school.sptech.server.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_media")
    private Integer idMedia;

    @Column(name = "TYPE")
    @NotNull
    private String type;

    @Column(name = "path")
    private String path;

    public Media(Integer idMedia, String type, String path) {
        this.idMedia = idMedia;
        this.type = type;
        this.path = path;
    }
    public Media(String type, String path) {
        this.type = type;
        this.path = path;
    }

    public Media() {
    }

//    public Media(String contentType, String format) {
//        this.type = type;
//        this.path = path;
//    }

    public Integer getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
