package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.ServicesScheduling;

import java.util.List;

public interface ServicesSchedulingRepository extends JpaRepository<ServicesScheduling, Integer> {
    List<ServicesScheduling> findByWorkerIdAndStatus(Integer id, String status);
    List<ServicesScheduling> findByCustomerIdAndStatusOrStatus(Integer id, String status, String stus2);
}
