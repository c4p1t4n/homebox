package school.sptech.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.server.model.Category;
import school.sptech.server.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findByNameAndWorkerId(String name, Integer idWorker);

    List<Category> findDistinctCategoryByWorkerId(Integer idWorker);
}
