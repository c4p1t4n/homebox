package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import school.sptech.server.model.Msg;
import school.sptech.server.response.MsgJoinChatHasMsg;

import java.util.List;

public interface MsgRepository extends JpaRepository<Msg, Integer> {
    Msg findByAutomaticAndFkUser(char automatic, int fkUser);

    boolean existsByFkUser(Integer fkUser);

    List<Msg> findByFkUser(Integer fkUser);

    @Query("select new school.sptech.server.response.MsgJoinChatHasMsg(m.idMsg, m.message, m.automatic, m.fkUser, chm.fkChat, chm.sendDate, chm.seen) from Msg m JOIN ChatHasMsg chm ON m.idMsg = chm.fkMsg AND chm.fkChat = ?1")
    List<MsgJoinChatHasMsg> findByChat(Integer fkChat);

    @Query("select new school.sptech.server.response.MsgJoinChatHasMsg(m.idMsg, m.message, m.automatic, m.fkUser, chm.fkChat, chm.sendDate, chm.seen) from Msg m left JOIN ChatHasMsg chm ON m.idMsg = chm.fkMsg")
    List<MsgJoinChatHasMsg> findAllWithChat();
}
