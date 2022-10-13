package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Chats;

public interface ChatsRepository extends JpaRepository<Chats, Integer> {
    boolean existsByUserIdAndUserId2(Integer id, Integer id2);
}
