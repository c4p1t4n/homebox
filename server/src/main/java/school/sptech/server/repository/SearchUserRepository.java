package school.sptech.server.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import school.sptech.server.id.UserHasSearchId;
import school.sptech.server.model.SearchUser;
import school.sptech.server.model.Search;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface SearchUserRepository extends JpaRepository<SearchUser, UserHasSearchId> {

    List<Search> findAllByFkUser(Integer fkUser);

    void deleteAllByIdInBatch(Iterable<UserHasSearchId> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<SearchUser> entities);

    void deleteInBatch(Iterable<SearchUser> entities);

    List<SearchUser> findAll();

    List<SearchUser> findAll(Sort sort);

    <S extends SearchUser> List<S> findAll(Example<S> example);

    <S extends SearchUser> List<S> findAll(Example<S> example, Sort sort);

    List<SearchUser> findAllById(Iterable<UserHasSearchId> ids);

    void flush();

    SearchUser getById(UserHasSearchId id);

    SearchUser getOne(UserHasSearchId id);

    <S extends SearchUser> List<S> saveAll(Iterable<S> entities);

    <S extends SearchUser> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends SearchUser> S saveAndFlush(S entity);

    Page<SearchUser> findAll(Pageable pageable);

    long count();

    void delete(SearchUser entity);

    void deleteAll();

    void deleteAll(Iterable<? extends SearchUser> entities);

    void deleteAllById(Iterable<? extends UserHasSearchId> ids);

    void deleteById(UserHasSearchId id);

    boolean existsById(UserHasSearchId id);

    Optional<SearchUser> findById(UserHasSearchId id);

    <S extends SearchUser> S save(S entity);

    <S extends SearchUser> long count(Example<S> example);

    <S extends SearchUser> boolean exists(Example<S> example);

    <S extends SearchUser> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends SearchUser, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends SearchUser> Optional<S> findOne(Example<S> example);
}
