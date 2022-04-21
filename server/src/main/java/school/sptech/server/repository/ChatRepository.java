package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.server.model.Chat;
import school.sptech.server.model.Staff;

import java.time.LocalDate;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query("")
    List<Chat> chat(int id);
}

/*
* select user.name, chat_has_msg.send_date, msg.menssage
* from chatJoin as(
*   select * from chat join user_chat
*                       on chat.id_chat = user_chat.fk_chat
*                       and fk_user = {}
* )
* join user_chat on chatJoin.id = user_chat.fk_chat
* join user on user.id_user = user_chat.fk_user
* join chat_has_msg on  chatJoin.id = chat_has_msg.fk_chat
* join msg on msg.id_msg = chat_has_msg
* oredr by chat_has_msg.send_date
* */