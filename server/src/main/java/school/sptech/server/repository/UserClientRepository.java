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

import school.sptech.server.model.UserCliente;

public interface UserClientRepository extends JpaRepository<UserCliente, Integer> {

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<UserCliente> entities);

    void deleteInBatch(Iterable<UserCliente> entities);

    List<UserCliente> findAll();

    List<UserCliente> findAll(Sort sort);

    <S extends UserCliente> List<S> findAll(Example<S> example);

    <S extends UserCliente> List<S> findAll(Example<S> example, Sort sort);

    List<UserCliente> findAllById(Iterable<Integer> ids);

    void flush();

    UserCliente getById(Integer id);

    UserCliente getOne(Integer id);

    <S extends UserCliente> List<S> saveAll(Iterable<S> entities);

    <S extends UserCliente> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends UserCliente> S saveAndFlush(S entity);

    Page<UserCliente> findAll(Pageable pageable);

    long count();

    void delete(UserCliente entity);

    void deleteAll();

    void deleteAll(Iterable<? extends UserCliente> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<UserCliente> findById(Integer id);

    <S extends UserCliente> S save(S entity);

    <S extends UserCliente> long count(Example<S> example);

    <S extends UserCliente> boolean exists(Example<S> example);

    <S extends UserCliente> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends UserCliente, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends UserCliente> Optional<S> findOne(Example<S> example);

}
