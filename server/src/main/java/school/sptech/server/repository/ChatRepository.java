package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Chat;
import school.sptech.server.model.Staff;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
