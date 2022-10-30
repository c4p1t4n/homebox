package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.server.model.ServicesStatusRecent;

import java.util.List;

public interface ServicesStatusRecentRepository  extends JpaRepository<ServicesStatusRecent, Integer> {
    List<ServicesStatusRecent> findByWorkerIdAndStatus(Integer id, String status);

    @Query("select new school.sptech.server.model.ServicesStatusRecent(s.workerId, s.customerId, s.idScheduling, s.nameService, s.serviceDescription, s.address, s.price, s.date, s.status, s.deleted) from ServicesStatusRecent s where s.customerId=?1 and (s.status=?2 or s.status=?3)")
    List<ServicesStatusRecent> findByCustomerIdAndStatusOrStatus(Integer id, String status, String stus2);
}


