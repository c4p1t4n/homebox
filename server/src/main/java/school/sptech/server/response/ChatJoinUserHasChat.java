package school.sptech.server.response;

import java.time.LocalDate;

public class ChatJoinUserHasChat {

    private Integer idChat;

    private LocalDate openingDate;

    private Integer fkUser;

    public ChatJoinUserHasChat(Integer idChat, LocalDate openingDate, Integer fkUser) {
        this.idChat = idChat;
        this.openingDate = openingDate;
        this.fkUser = fkUser;
    }

    public ChatJoinUserHasChat() {
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

}
