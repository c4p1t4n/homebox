package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Chat;


public interface ChatRepository extends JpaRepository<Chat, Integer> {


    //List<ChatJoinUserHasChat> findChatsPerUser(Integer idUser);
}
