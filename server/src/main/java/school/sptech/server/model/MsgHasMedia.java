package school.sptech.server.model;

import javax.persistence.*;

@Entity
@Table(name = "msg_has_media")
public class MsgHasMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_msg_has_media")
    private Integer id;

    @ManyToOne
    private Msg msg;

    @ManyToOne
    private Media media;

    public MsgHasMedia(Integer id, Msg msg, Media media) {
        this.id = id;
        this.msg = msg;
        this.media = media;
    }

    public MsgHasMedia(Msg msg, Media media) {
        this.msg = msg;
        this.media = media;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public MsgHasMedia() {
    }

}
