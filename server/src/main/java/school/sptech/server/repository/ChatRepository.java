package school.sptech.server.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.server.model.Chat;
import java.time.LocalDate;
import java.time.LocalDateTime;
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    //List<ChatJoinUserHasChat> findChatsPerUser(Integer idUser);
    @Query("select count(*) from Chat  where opening_date >= ?1 and opening_date <= CURRENT_DATE")
    Integer getCountChats(LocalDate date);
}