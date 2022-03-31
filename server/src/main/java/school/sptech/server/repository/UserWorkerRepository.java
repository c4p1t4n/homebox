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

import school.sptech.server.model.UserWorker;

public interface UserWorkerRepository extends JpaRepository<UserWorker, Integer> {

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<UserWorker> entities);

    void deleteInBatch(Iterable<UserWorker> entities);

    List<UserWorker> findAll();

    List<UserWorker> findAll(Sort sort);

    <S extends UserWorker> List<S> findAll(Example<S> example);

    <S extends UserWorker> List<S> findAll(Example<S> example, Sort sort);

    List<UserWorker> findAllById(Iterable<Integer> ids);

    void flush();

    UserWorker getById(Integer id);

    UserWorker getOne(Integer id);

    <S extends UserWorker> List<S> saveAll(Iterable<S> entities);

    <S extends UserWorker> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends UserWorker> S saveAndFlush(S entity);

    Page<UserWorker> findAll(Pageable pageable);

    long count();

    void delete(UserWorker entity);

    void deleteAll();

    void deleteAll(Iterable<? extends UserWorker> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<UserWorker> findById(Integer id);

    <S extends UserWorker> S save(S entity);

    <S extends UserWorker> long count(Example<S> example);

    <S extends UserWorker> boolean exists(Example<S> example);

    <S extends UserWorker> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends UserWorker, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends UserWorker> Optional<S> findOne(Example<S> example);

}
