package school.sptech.server.model;

import school.sptech.server.service.MsgHasMediaId;

import javax.persistence.*;

@Entity
@Table(name = "msg_has_media")
@IdClass(MsgHasMediaId.class)
public class MsgHasMedia {
    @Id
    @Column(name = "fk_msg")
    private Integer fkMsg;

    @Id
    @Column(name = "fk_media")
    private Integer fkMedia;

    public MsgHasMedia(Integer fkMsg, Integer fkMedia) {
        this.fkMsg = fkMsg;
        this.fkMedia = fkMedia;
    }

    public MsgHasMedia() {
    }

    public Integer getFkMsg() {
        return fkMsg;
    }

    public void setFkMsg(Integer fkMsg) {
        this.fkMsg = fkMsg;
    }

    public Integer getFkMedia() {
        return fkMedia;
    }

    public void setFkMedia(Integer fkMedia) {
        this.fkMedia = fkMedia;
    }
}
