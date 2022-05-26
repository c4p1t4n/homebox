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

import school.sptech.server.id.MsgHasMediaId;
import school.sptech.server.model.MsgHasMedia;

public interface MsgHasMediaRepository extends JpaRepository<MsgHasMedia, MsgHasMediaId> {

    @Override
    void deleteAllByIdInBatch(Iterable<MsgHasMediaId> ids);

    @Override
    void deleteAllInBatch();

    @Override
    void deleteAllInBatch(Iterable<MsgHasMedia> entities);

    @Override
    void deleteInBatch(Iterable<MsgHasMedia> entities);

    @Override
    List<MsgHasMedia> findAll();

    @Override
    List<MsgHasMedia> findAll(Sort sort);

    @Override
    <S extends MsgHasMedia> List<S> findAll(Example<S> example);

    @Override
    <S extends MsgHasMedia> List<S> findAll(Example<S> example, Sort sort);

    @Override
    List<MsgHasMedia> findAllById(Iterable<MsgHasMediaId> ids);

    @Override
    void flush();

    @Override
    MsgHasMedia getById(MsgHasMediaId id);

    @Override
    MsgHasMedia getOne(MsgHasMediaId id);

    @Override
    <S extends MsgHasMedia> List<S> saveAll(Iterable<S> entities);

    @Override
    <S extends MsgHasMedia> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    <S extends MsgHasMedia> S saveAndFlush(S entity);

    @Override
    Page<MsgHasMedia> findAll(Pageable pageable);

    @Override
    long count();

    @Override
    void delete(MsgHasMedia entity);

    @Override
    void deleteAll();

    @Override
    void deleteAll(Iterable<? extends MsgHasMedia> entities);

    @Override
    void deleteAllById(Iterable<? extends MsgHasMediaId> ids);

    @Override
    void deleteById(MsgHasMediaId id);

    @Override
    boolean existsById(MsgHasMediaId id);

    @Override
    Optional<MsgHasMedia> findById(MsgHasMediaId id);

    @Override
    <S extends MsgHasMedia> S save(S entity);

    @Override
    <S extends MsgHasMedia> long count(Example<S> example);

    @Override
    <S extends MsgHasMedia> boolean exists(Example<S> example);

    @Override
    <S extends MsgHasMedia> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends MsgHasMedia, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction);

    @Override
    <S extends MsgHasMedia> Optional<S> findOne(Example<S> example);
}