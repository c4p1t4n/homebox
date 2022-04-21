package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.server.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findByAutomaticAndfkUser(char automatic, int fkUser);

    @Query("")
    List<Message> msg();
}
