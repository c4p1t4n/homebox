package school.sptech.server.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import school.sptech.server.model.Chat;
import school.sptech.server.model.ChatHasMsg;

public interface ChatHasMsgRepository extends JpaRepository<ChatHasMsg, Integer> {

    boolean existsByMsgIdMsgAndChatIdChat(int msg, int chat);

    @Transactional
    @Modifying
    @Query("update ChatHasMsg chm set chm.seen = 'y' where chm.msg.idMsg = ?1 and chm.chat.idChat = ?2")
    void readNotification(Integer idMsg, Integer idChat);

    ChatHasMsg findTop1ByChatIdChatOrderBySendDateDesc(Integer idChat);

    List<ChatHasMsg> findAllByChat(Optional<Chat> chat);

    List<ChatHasMsg> findAllByChatIsNotNull();

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<ChatHasMsg> entities);

    void deleteInBatch(Iterable<ChatHasMsg> entities);

    List<ChatHasMsg> findAll();

    List<ChatHasMsg> findAll(Sort sort);

    <S extends ChatHasMsg> List<S> findAll(Example<S> example);

    <S extends ChatHasMsg> List<S> findAll(Example<S> example, Sort sort);

    List<ChatHasMsg> findAllById(Iterable<Integer> ids);

    void flush();

    ChatHasMsg getById(Integer id);

    ChatHasMsg getOne(Integer id);

    <S extends ChatHasMsg> List<S> saveAll(Iterable<S> entities);

    <S extends ChatHasMsg> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ChatHasMsg> S saveAndFlush(S entity);

    Page<ChatHasMsg> findAll(Pageable pageable);

    long count();

    void delete(ChatHasMsg entity);

    void deleteAll();

    void deleteAll(Iterable<? extends ChatHasMsg> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<ChatHasMsg> findById(Integer id);

    <S extends ChatHasMsg> S save(S entity);

    <S extends ChatHasMsg> long count(Example<S> example);

    <S extends ChatHasMsg> boolean exists(Example<S> example);

    <S extends ChatHasMsg> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ChatHasMsg, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends ChatHasMsg> Optional<S> findOne(Example<S> example);

}
