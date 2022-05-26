package school.sptech.server.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import school.sptech.server.model.Notification;
import school.sptech.server.model.UserHasNotification;
import school.sptech.server.model.keys.UserHasNotificationKey;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

 /*
    @Query("select new school.sptech.server.response.NotificationJoinUserNotificationResponse(un.fkUser, un.notificationDate, un.seen, n.id, n.title, n.message) from Notification n join UserHasNotification un on n.id = un.fkNotification where un.fkUser = ?1 ")
    List<NotificationJoinUserNotificationResponse> notificationsPerUserList(Integer fkUser);
*/



    List<UserHasNotification> findById(UserHasNotificationKey id);


    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Notification> entities);

    void deleteInBatch(Iterable<Notification> entities);

    List<Notification> findAll();

    List<Notification> findAll(Sort sort);

    <S extends Notification> List<S> findAll(Example<S> example);

    <S extends Notification> List<S> findAll(Example<S> example, Sort sort);

    List<Notification> findAllById(Iterable<Integer> ids);

    void flush();

    Notification getById(Integer id);

    Notification getOne(Integer id);

    <S extends Notification> List<S> saveAll(Iterable<S> entities);

    <S extends Notification> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Notification> S saveAndFlush(S entity);

    Page<Notification> findAll(Pageable pageable);

    long count();

    void delete(Notification entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Notification> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<Notification> findById(Integer id);

    <S extends Notification> S save(S entity);

    <S extends Notification> long count(Example<S> example);

    <S extends Notification> boolean exists(Example<S> example);

    <S extends Notification> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Notification, R> R findBy(Example<S> example,
                                         Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends Notification> Optional<S> findOne(Example<S> example);



}
