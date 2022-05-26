package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.User;
import school.sptech.server.model.UserHasChat;
import school.sptech.server.model.keys.UserHasChatKey;

import java.util.List;

public interface UserHasChatRepository extends JpaRepository<UserHasChat, UserHasChatKey> {


    List<UserHasChat> findChatByUser(User user);


}
