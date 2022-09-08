package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.server.model.InterestAccess;

import java.time.LocalDate;

public interface InterestAccessRepository extends JpaRepository<InterestAccess, Integer> {


    @Query(value="select COUNT(ia) from school.sptech.server.model.InterestAccess ia where ia.user.id = ?1 and  ia.accessDate BETWEEN  DATE(?2)  and DATE(CURRENT_DATE)")
    Integer countIntestAcess(Integer id_user, LocalDate date);

}
