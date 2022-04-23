package school.sptech.server.response;

import java.time.LocalDate;

public class MsgJoinChatHasMsg {

    private Integer idMsg;

    private String message;

    private Character automatic;

    private int fkUser;

    private Integer fkChat;

    private LocalDate sendDate;

    private char seen;

    public MsgJoinChatHasMsg() {
    }

    public MsgJoinChatHasMsg(Integer idMsg, String message, Character automatic, int fkUser, Integer fkChat,
            LocalDate sendDate, char seen) {
        this.idMsg = idMsg;
        this.message = message;
        this.automatic = automatic;
        this.fkUser = fkUser;
        this.fkChat = fkChat;
        this.sendDate = sendDate;
        this.seen = seen;
    }

    public Integer getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(Integer idMsg) {
        this.idMsg = idMsg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Character getAutomatic() {
        return automatic;
    }

    public void setAutomatic(Character automatic) {
        this.automatic = automatic;
    }

    public int getFkUser() {
        return fkUser;
    }

    public void setFkUser(int fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getFkChat() {
        return fkChat;
    }

    public void setFkChat(Integer fkChat) {
        this.fkChat = fkChat;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public char getSeen() {
        return seen;
    }

    public void setSeen(char seen) {
        this.seen = seen;
    }
}