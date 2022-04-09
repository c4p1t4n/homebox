package school.sptech.server.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.transaction.annotation.Transactional;

import school.sptech.server.model.UserHasNotification;
import school.sptech.server.service.UserHasNotificationId;

public interface UserHasNotificationRepository extends JpaRepository<UserHasNotification, UserHasNotificationId> {

    @Transactional
    @Modifying
    @Query("update UserHasNotification un set un.seen = 'y' where un.fkNotification = ?1 AND un.fkUser = ?2")
    void readNotification(Integer idNotification, Integer fkUser);

    void deleteAllByIdInBatch(Iterable<UserHasNotificationId> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<UserHasNotification> entities);

    void deleteInBatch(Iterable<UserHasNotification> entities);

    List<UserHasNotification> findAll();

    List<UserHasNotification> findAll(Sort sort);

    <S extends UserHasNotification> List<S> findAll(Example<S> example);

    <S extends UserHasNotification> List<S> findAll(Example<S> example, Sort sort);

    List<UserHasNotification> findAllById(Iterable<UserHasNotificationId> ids);

    void flush();

    UserHasNotification getById(UserHasNotificationId id);

    UserHasNotification getOne(UserHasNotificationId id);

    <S extends UserHasNotification> List<S> saveAll(Iterable<S> entities);

    <S extends UserHasNotification> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends UserHasNotification> S saveAndFlush(S entity);

    Page<UserHasNotification> findAll(Pageable pageable);

    long count();

    void delete(UserHasNotification entity);

    void deleteAll();

    void deleteAll(Iterable<? extends UserHasNotification> entities);

    void deleteAllById(Iterable<? extends UserHasNotificationId> ids);

    void deleteById(UserHasNotificationId id);

    boolean existsById(UserHasNotificationId id);

    Optional<UserHasNotification> findById(UserHasNotificationId id);

    <S extends UserHasNotification> S save(S entity);

    <S extends UserHasNotification> long count(Example<S> example);

    <S extends UserHasNotification> boolean exists(Example<S> example);

    <S extends UserHasNotification> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends UserHasNotification, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends UserHasNotification> Optional<S> findOne(Example<S> example);
}
