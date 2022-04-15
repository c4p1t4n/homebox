package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.UserChat;
import school.sptech.server.service.UserChatId;

public interface UserChatRepository extends JpaRepository<UserChat, UserChatId> {
}
