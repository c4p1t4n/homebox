package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.server.model.Chat;
import school.sptech.server.response.ChatJoinUserHasChat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query("select new school.sptech.server.response.ChatJoinUserHasChat(c.idChat, c.openingDate, uhc.fkUser) from Chat c join  UserHasChat uhc on c.idChat = uhc.fkChat where uhc.fkUser = ?1")
    List<ChatJoinUserHasChat> findChatsPerUser(Integer idUser);
}
