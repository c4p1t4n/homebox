package school.sptech.server.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import school.sptech.server.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Query(value = "select user.name  from service  join user on worker_id_user=id_user where id_service = ?1",nativeQuery=true)
    String getNameProvider(Integer id_service);
    boolean existsByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<User> entities);

    void deleteInBatch(Iterable<User> entities);

    List<User> findAll();

    List<User> findAll(Sort sort);

    <S extends User> List<S> findAll(Example<S> example);

    <S extends User> List<S> findAll(Example<S> example, Sort sort);

    List<User> findAllById(Iterable<Integer> ids);

    List<User> findTop3ByType(String type);

    void flush();

    User getById(Integer id);

    User getOne(Integer id);

    <S extends User> List<S> saveAll(Iterable<S> entities);

    <S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends User> S saveAndFlush(S entity);

    Page<User> findAll(Pageable pageable);

    long count();

    void delete(User entity);

    void deleteAll();

    void deleteAll(Iterable<? extends User> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<User> findById(Integer id);

    <S extends User> S save(S entity);

    <S extends User> long count(Example<S> example);

    <S extends User> boolean exists(Example<S> example);

    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends User, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends User> Optional<S> findOne(Example<S> example);

//    Category findByEmail(String email);
//
//    @Transactional
//    @Modifying
//    @Query("update User u set u.token = ?1 where u.id_user = ?2")
//    void updateToken(String token, Integer idUser);
}
