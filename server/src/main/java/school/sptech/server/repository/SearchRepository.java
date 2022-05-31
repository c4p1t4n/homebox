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

import school.sptech.server.model.Search;

public interface SearchRepository extends JpaRepository<Search, Integer> {
    Search findByValue(String search);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Search> entities);

    void deleteInBatch(Iterable<Search> entities);

    List<Search> findAll();

    List<Search> findAll(Sort sort);

    <S extends Search> List<S> findAll(Example<S> example);

    <S extends Search> List<S> findAll(Example<S> example, Sort sort);

    List<Search> findAllById(Iterable<Integer> ids);

    List<Search> findByIdSearch(Integer idSearch);

    void flush();

    Search getById(Integer id);

    Search getOne(Integer id);

    <S extends Search> List<S> saveAll(Iterable<S> entities);

    <S extends Search> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Search> S saveAndFlush(S entity);

    Page<Search> findAll(Pageable pageable);

    long count();

    void delete(Search entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Search> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<Search> findById(Integer id);

    <S extends Search> S save(S entity);

    <S extends Search> long count(Example<S> example);

    <S extends Search> boolean exists(Example<S> example);

    <S extends Search> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Search, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends Search> Optional<S> findOne(Example<S> example);
}
