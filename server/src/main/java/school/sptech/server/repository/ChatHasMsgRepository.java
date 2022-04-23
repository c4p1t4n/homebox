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

import school.sptech.server.model.ChatHasMsg;
import school.sptech.server.service.ChatHasMsgId;

public interface ChatHasMsgRepository extends JpaRepository<ChatHasMsg, ChatHasMsgId> {
    @Transactional
    @Modifying
    @Query("update ChatHasMsg chm set chm.seen = 'y' where chm.fkMsg = ?1 AND chm.fkChat = ?2")
    void readNotification(Integer idMsg, Integer fkChat);

    void deleteAllByIdInBatch(Iterable<ChatHasMsgId> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<ChatHasMsg> entities);

    void deleteInBatch(Iterable<ChatHasMsg> entities);

    List<ChatHasMsg> findAll();

    List<ChatHasMsg> findAll(Sort sort);

    <S extends ChatHasMsg> List<S> findAll(Example<S> example);

    <S extends ChatHasMsg> List<S> findAll(Example<S> example, Sort sort);

    List<ChatHasMsg> findAllById(Iterable<ChatHasMsgId> ids);

    void flush();

    ChatHasMsg getById(ChatHasMsgId id);

    ChatHasMsg getOne(ChatHasMsgId id);

    <S extends ChatHasMsg> List<S> saveAll(Iterable<S> entities);

    <S extends ChatHasMsg> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends ChatHasMsg> S saveAndFlush(S entity);

    Page<ChatHasMsg> findAll(Pageable pageable);

    long count();

    void delete(ChatHasMsg entity);

    void deleteAll();

    void deleteAll(Iterable<? extends ChatHasMsg> entities);

    void deleteAllById(Iterable<? extends ChatHasMsgId> ids);

    void deleteById(ChatHasMsgId id);

    boolean existsById(ChatHasMsgId id);

    Optional<ChatHasMsg> findById(ChatHasMsgId id);

    <S extends ChatHasMsg> S save(S entity);

    <S extends ChatHasMsg> long count(Example<S> example);

    <S extends ChatHasMsg> boolean exists(Example<S> example);

    <S extends ChatHasMsg> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends ChatHasMsg, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends ChatHasMsg> Optional<S> findOne(Example<S> example);
}
