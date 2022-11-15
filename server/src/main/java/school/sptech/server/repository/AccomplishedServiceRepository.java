package school.sptech.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.server.model.AccomplishedService;

public interface AccomplishedServiceRepository extends JpaRepository<AccomplishedService, Integer> {
    boolean existsBySchedulingIdScheduling(Integer id);

    Optional<AccomplishedService> findBySchedulingIdScheduling(Integer id);

    Optional<AccomplishedService> findById(Integer id);
}
