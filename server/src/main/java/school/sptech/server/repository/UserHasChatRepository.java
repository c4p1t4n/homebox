package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.UserHasChat;
import school.sptech.server.service.UserChatId;

public interface UserHasChatRepository extends JpaRepository<UserHasChat, UserChatId> {
}
