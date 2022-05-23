package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.server.model.Chat;
import school.sptech.server.response.ChatJoinUserHasChat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {


    //List<ChatJoinUserHasChat> findChatsPerUser(Integer idUser);
}
