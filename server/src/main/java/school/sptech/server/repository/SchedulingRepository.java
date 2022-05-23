package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.server.model.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Integer> {
    boolean existsByCustomerIdAndServiceIdService(Integer iduser, Integer serviceId);
}
