package school.sptech.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import school.sptech.server.model.Category;
import school.sptech.server.model.Service;
import school.sptech.server.model.User;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findByNameAndWorkerId(String name, Integer idWorker);

    @Query("select distinct s.category from Service s where s.worker.id = ?1")
    List<Category> findDistinctByWorkerId(Integer idWorker);

    @Query("select distinct s.worker from Service s where s.category.name LIKE %:name%")
    List<User> findByCategoryNameContainsIgnoreCase(String name);

    @Query("select distinct s.worker from Service s where s.worker.name LIKE %:info%")
    List<User> searchUsers(String info);

    boolean existsByWorkerId(Integer idWorker);

}
