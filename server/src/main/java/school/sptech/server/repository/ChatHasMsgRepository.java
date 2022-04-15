package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.ChatHasMsg;
import school.sptech.server.model.UserChat;
import school.sptech.server.service.ChatHasMsgId;
import school.sptech.server.service.UserChatId;

public interface ChatHasMsgRepository extends JpaRepository<ChatHasMsg, ChatHasMsgId> {
}
