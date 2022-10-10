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

import school.sptech.server.model.Staff;
import school.sptech.server.model.User;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Staff findByEmailAndPassword(String email, String password);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Staff> entities);

    void deleteInBatch(Iterable<Staff> entities);

    List<Staff> findAll();

    List<Staff> findAll(Sort sort);

    <S extends Staff> List<S> findAll(Example<S> example);

    <S extends Staff> List<S> findAll(Example<S> example, Sort sort);

    List<Staff> findAllById(Iterable<Integer> ids);

    void flush();

    Staff getById(Integer id);

    Staff getOne(Integer id);

    <S extends Staff> List<S> saveAll(Iterable<S> entities);

    <S extends Staff> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Staff> S saveAndFlush(S entity);

    Page<Staff> findAll(Pageable pageable);

    long count();

    void delete(Staff entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Staff> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Optional<Staff> findById(Integer id);

    <S extends Staff> S save(S entity);

    <S extends Staff> long count(Example<S> example);

    <S extends Staff> boolean exists(Example<S> example);

    <S extends Staff> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Staff, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction);

    <S extends Staff> Optional<S> findOne(Example<S> example);
}
