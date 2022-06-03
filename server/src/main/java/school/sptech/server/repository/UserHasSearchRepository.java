package school.sptech.server.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import school.sptech.server.model.UserHasSearch;
import school.sptech.server.model.Search;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface UserHasSearchRepository extends JpaRepository<UserHasSearch, Integer> {

    List<Search> findAllByUserId(Integer fkUser);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<UserHasSearch> entities);

    void deleteInBatch(Iterable<UserHasSearch> entities);

    List<UserHasSearch> findAll();

    List<UserHasSearch> findAll(Sort sort);

    <S extends UserHasSearch> List<S> findAll(Example<S> example);

    <S extends UserHasSearch> List<S> findAll(Example<S> example, Sort sort);

    List<UserHasSearch> findAllById(Iterable<Integer> ids);

    void flush();

    UserHasSearch getById(Integer id);

    UserHasSearch getOne(Integer id);

    <S extends UserHasSearch> List<S> saveAll(Iterable<S> entities);

    <S extends UserHasSearch> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends UserHasSearch> S saveAndFlush(S entity);

    Page<UserHasSearch> findAll(Pageable pageable);

    long count();

    void delete(UserHasSearch entity);

    void deleteAll();

    void deleteAll(Iterable<? extends UserHasSearch> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<UserHasSearch> findById(Integer id);

    <S extends UserHasSearch> S save(S entity);

    <S extends UserHasSearch> long count(Example<S> example);

    <S extends UserHasSearch> boolean exists(Example<S> example);

    <S extends UserHasSearch> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends UserHasSearch, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends UserHasSearch> Optional<S> findOne(Example<S> example);
}
