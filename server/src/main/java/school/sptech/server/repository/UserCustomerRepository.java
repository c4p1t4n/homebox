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

import school.sptech.server.model.UserCustomer;

public interface UserCustomerRepository extends JpaRepository<UserCustomer, Integer> {

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<UserCustomer> entities);

    void deleteInBatch(Iterable<UserCustomer> entities);

    List<UserCustomer> findAll();

    List<UserCustomer> findAll(Sort sort);

    <S extends UserCustomer> List<S> findAll(Example<S> example);

    <S extends UserCustomer> List<S> findAll(Example<S> example, Sort sort);

    List<UserCustomer> findAllById(Iterable<Integer> ids);

    void flush();

    UserCustomer getById(Integer id);

    UserCustomer getOne(Integer id);

    <S extends UserCustomer> List<S> saveAll(Iterable<S> entities);

    <S extends UserCustomer> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends UserCustomer> S saveAndFlush(S entity);

    Page<UserCustomer> findAll(Pageable pageable);

    long count();

    void delete(UserCustomer entity);

    void deleteAll();

    void deleteAll(Iterable<? extends UserCustomer> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<UserCustomer> findById(Integer id);

    <S extends UserCustomer> S save(S entity);

    <S extends UserCustomer> long count(Example<S> example);

    <S extends UserCustomer> boolean exists(Example<S> example);

    <S extends UserCustomer> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends UserCustomer, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends UserCustomer> Optional<S> findOne(Example<S> example);

}
