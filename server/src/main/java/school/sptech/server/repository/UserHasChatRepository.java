package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import school.sptech.server.model.User;
import school.sptech.server.model.UserHasChat;

import java.util.List;

public interface UserHasChatRepository extends JpaRepository<UserHasChat, Integer> {

    @Query(value = "select uhc from school.sptech.server.model.UserHasChat uhc where uhc.user.id <> ?1 and uhc.chat.idChat in (select uhc2.chat.idChat from school.sptech.server.model.UserHasChat uhc2 where uhc2.user.id = ?1)")
    List<UserHasChat> findByUserWithPartner(Integer userId);

    @Query(value = "select uhc from school.sptech.server.model.UserHasChat uhc join User u on uhc.user.id = u.id where uhc.user.id <> ?1 and u.name like %?2% and uhc.chat.idChat in (select uhc2.chat.idChat from school.sptech.server.model.UserHasChat uhc2 where uhc2.user.id = ?1)")
    List<UserHasChat> findByUserWithSpecificPartner(Integer userId, String name);

    List<UserHasChat> findByUser(User user);

}
