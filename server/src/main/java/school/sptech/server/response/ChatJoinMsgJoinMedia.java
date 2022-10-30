package school.sptech.server.response;

import java.time.LocalDateTime;

public class ChatJoinMsgJoinMedia {
    private Integer idChat;
    private LocalDateTime sendDate;
    private String message;
    private Integer userId;
    private String type;
    private String path;

    public ChatJoinMsgJoinMedia(int idChat, LocalDateTime sendDate, String message, int userId, String type, String path) {
        this.idChat = idChat;
        this.sendDate = sendDate;
        this.message = message;
        this.userId = userId;
        this.type = type;
        this.path = path;
    }


    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }
}
