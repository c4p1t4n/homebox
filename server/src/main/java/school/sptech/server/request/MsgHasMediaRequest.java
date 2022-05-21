package school.sptech.server.request;

public class MsgHasMediaRequest {

    private Integer fkMsg;

    private Integer fkMedia;

    public MsgHasMediaRequest(Integer fkMsg, Integer fkMedia) {
        this.fkMsg = fkMsg;
        this.fkMedia = fkMedia;
    }

    public MsgHasMediaRequest() {
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
