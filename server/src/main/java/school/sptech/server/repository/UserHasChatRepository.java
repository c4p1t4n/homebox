package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.server.id.UserChatId;
import school.sptech.server.model.UserHasChat;

public interface UserHasChatRepository extends JpaRepository<UserHasChat, UserChatId> {
}
