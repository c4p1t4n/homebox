package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findByAutomaticAndfkUser(char automatic, int fkUser);
}
