package school.sptech.server.id;

import java.io.Serializable;

public class MsgHasMediaId implements Serializable {
    private Integer fkMedia;
    private Integer fkMsg;

    public MsgHasMediaId() {
    }

    public MsgHasMediaId(Integer fkMedia, Integer fkMsg) {
        this.fkMedia = fkMedia;
        this.fkMsg = fkMsg;
    }

    public Integer getfkMedia() {
        return fkMedia;
    }

    public void setfkMedia(Integer fkMedia) {
        this.fkMedia = fkMedia;
    }

    public Integer getfkMsg() {
        return fkMsg;
    }

    public void setfkMsg(Integer fkMsg) {
        this.fkMsg = fkMsg;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fkMsg == null) ? 0 : fkMsg.hashCode());
        result = prime * result + ((fkMedia == null) ? 0 : fkMedia.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MsgHasMediaId other = (MsgHasMediaId) obj;
        if (fkMsg == null) {
            if (other.fkMsg != null)
                return false;
        } else if (!fkMsg.equals(other.fkMsg))
            return false;
        if (fkMedia == null) {
            if (other.fkMedia != null)
                return false;
        } else if (!fkMedia.equals(other.fkMedia))
            return false;
        return true;
    }

}
