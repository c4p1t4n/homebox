package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.server.model.ServicesScheduling;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServicesSchedulingRepository extends JpaRepository<ServicesScheduling, Integer> {
    List<ServicesScheduling> findByWorkerIdAndStatus(Integer id, String status);
    @Query("select new school.sptech.server.model.ServicesScheduling(s.idAccomplishedService,s.idService,s.id, s.workerId, s.customerId, s.nameService, s.serviceDescription, s.address, s.price, s.date, s.status) from ServicesScheduling s where s.customerId=?1 and (s.status=?2 or s.status=?3)")
    List<ServicesScheduling> findByCustomerIdAndStatusOrStatus(Integer id, String status, String stus2);

    @Query("select idAccomplishedService from ServicesScheduling where customer_id = ?1 and status='scheduled' and date >= ?2 and date <=?3 order by date")
    List<Integer> findIdAccomplishedService(Integer customer_id, LocalDate dia , LocalDate diaSucessor);
}
