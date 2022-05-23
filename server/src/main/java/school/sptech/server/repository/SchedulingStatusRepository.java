package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.server.model.SchedulingStatus;

public interface SchedulingStatusRepository extends JpaRepository<SchedulingStatus, Integer> {
    SchedulingStatus findFirstBySchedulingIdSchedulingOrderByStatusDateDesc(Integer id);

    boolean existsByServiceStatusAndSchedulingIdScheduling(String status, Integer schedulingId);
}
