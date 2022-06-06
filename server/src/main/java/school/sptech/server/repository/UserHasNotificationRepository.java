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

import school.sptech.server.model.User;
import school.sptech.server.model.UserHasNotification;

public interface UserHasNotificationRepository extends JpaRepository<UserHasNotification, Integer> {

    List<UserHasNotification> findByUserIdAndNotificationId(int idUser, int notificationId);

    boolean existsByUserIdAndNotificationId(int idUser, int notificationId);

    @Transactional
    @Modifying
    @Query("update UserHasNotification un set un.seen = 'y' where un.user.id = ?1 and un.notification.id = ?2")
    void readNotification(Integer idUser, Integer idNotification);

    List<UserHasNotification> findByUser(Optional<User> user);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<UserHasNotification> entities);

    void deleteInBatch(Iterable<UserHasNotification> entities);

    List<UserHasNotification> findAll();

    List<UserHasNotification> findAll(Sort sort);

    <S extends UserHasNotification> List<S> findAll(Example<S> example);

    <S extends UserHasNotification> List<S> findAll(Example<S> example, Sort sort);

    List<UserHasNotification> findAllById(Iterable<Integer> ids);

    void flush();

    UserHasNotification getById(Integer id);

    UserHasNotification getOne(Integer id);

    <S extends UserHasNotification> List<S> saveAll(Iterable<S> entities);

    <S extends UserHasNotification> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends UserHasNotification> S saveAndFlush(S entity);

    Page<UserHasNotification> findAll(Pageable pageable);

    long count();

    void delete(UserHasNotification entity);

    void deleteAll();

    void deleteAll(Iterable<? extends UserHasNotification> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<UserHasNotification> findById(Integer id);

    <S extends UserHasNotification> S save(S entity);

    <S extends UserHasNotification> long count(Example<S> example);

    <S extends UserHasNotification> boolean exists(Example<S> example);

    <S extends UserHasNotification> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends UserHasNotification, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends UserHasNotification> Optional<S> findOne(Example<S> example);

}
