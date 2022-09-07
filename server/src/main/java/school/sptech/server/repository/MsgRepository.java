package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Msg;

import java.util.List;

public interface MsgRepository extends JpaRepository<Msg, Integer> {
    Msg findByAutomaticAndUserId(char automatic, int fkUser);

    boolean existsByUserId(Integer fkUser);

    List<Msg> findByUserId(Integer fkUser);

 //   List<MsgJoinChatHasMsg> findAllWithChat();

    // List<MsgJoinChatHasMsg> findAllWithChat();


}
