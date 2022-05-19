package school.sptech.server.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.server.model.ChatHasMsg;
import school.sptech.server.model.Search;
import school.sptech.server.model.SearchUser;
import school.sptech.server.response.NotificationJoinUserNotificationResponse;
import school.sptech.server.response.SearchJoinUserSearchResponse;
import school.sptech.server.service.ChatHasMsgId;
import school.sptech.server.service.UserHasSearchId;

import javax.persistence.EmbeddedId;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface SearchUserRepository extends JpaRepository<SearchUser, UserHasSearchId> {

    List<Search> findAllByFkUser(Integer fkUser);

/*
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

    <S extends ChatHasMsg> Optional<S> findOne(Example<S> example);*/
}
