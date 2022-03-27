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

import school.sptech.server.model.UserPrestador;

public interface UserPrestadorRepository extends JpaRepository<UserPrestador, Integer> {

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<UserPrestador> entities);

    void deleteInBatch(Iterable<UserPrestador> entities);

    List<UserPrestador> findAll();

    List<UserPrestador> findAll(Sort sort);

    <S extends UserPrestador> List<S> findAll(Example<S> example);

    <S extends UserPrestador> List<S> findAll(Example<S> example, Sort sort);

    List<UserPrestador> findAllById(Iterable<Integer> ids);

    void flush();

    UserPrestador getById(Integer id);

    UserPrestador getOne(Integer id);

    <S extends UserPrestador> List<S> saveAll(Iterable<S> entities);

    <S extends UserPrestador> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends UserPrestador> S saveAndFlush(S entity);

    Page<UserPrestador> findAll(Pageable pageable);

    long count();

    void delete(UserPrestador entity);

    void deleteAll();

    void deleteAll(Iterable<? extends UserPrestador> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<UserPrestador> findById(Integer id);

    <S extends UserPrestador> S save(S entity);

    <S extends UserPrestador> long count(Example<S> example);

    <S extends UserPrestador> boolean exists(Example<S> example);

    <S extends UserPrestador> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends UserPrestador, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends UserPrestador> Optional<S> findOne(Example<S> example);

}
