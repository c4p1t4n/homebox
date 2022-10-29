package school.sptech.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import school.sptech.server.model.Category;
import school.sptech.server.model.Service;
import school.sptech.server.model.User;
import school.sptech.server.response.UserSearchQueryResult;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findByNameAndWorkerId(String name, Integer idWorker);

    List<Service> findByWorkerIdAndDeletedNull(Integer idWorker);

    @Query("select distinct s.category from Service s where s.worker.id = ?1")
    List<Category> findDistinctByWorkerId(Integer idWorker);

    @Query("select distinct new school.sptech.server.response.UserSearchQueryResult(s.worker, s.category.name) from Service s where s.category.name LIKE %:name% and s.deleted is null")
    List<UserSearchQueryResult> findByCategoryNameContainsIgnoreCase(String name);

    @Query("select distinct s.worker from Service s where s.worker.name LIKE %:info%")
    List<User> UserHasSearchs(String info);

    boolean existsByWorkerId(Integer idWorker);

    boolean existsByidService(Integer idService);
    Service findByidService(Integer idService);

    Optional<Service> findByIdServiceAndDeletedNull(Integer id);
}
