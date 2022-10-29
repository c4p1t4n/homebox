package school.sptech.server.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.server.model.Scheduling;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
public interface SchedulingRepository extends JpaRepository<Scheduling, Integer> {
    boolean existsByCustomerIdAndServiceIdService(Integer iduser, Integer serviceId);
    //@Query("select count(distinct id_scheduling_status) from SchedulingStatus  where status_date >=  ?1 and  status_date <= CURRENT_DATE")
    //Integer getCountDistinctScheduling(LocalDate date);
   // @Query("select count(distinct id_scheduling_status) from SchedulingStatus")
    //Integer getCountSchedulingDone();


    // ----------------------------------------------------------------
    @Query("select count(*) from ServicesScheduling where status='done'  and  date>=  ?1 and  date <=  CURRENT_DATE   group by DATE(date)")
    List<Integer> getCountSchedulingLastSevenDaysDone(LocalDate date);
    @Query("select DATE(date) from ServicesScheduling where status='done' and  date>=  ?1 and  date <=  CURRENT_DATE   group by DATE(date)")
    List<Date> getSchedulingLastSevenDaysDone(LocalDate date);
    // ----------------------------------------------------------------
    @Query("select count(*) from ServicesScheduling where    date>=  ?1 and  date <=  CURRENT_DATE   group by DATE(date)")
    List<Integer> getCountSchedulingLastSevenDays(LocalDate date);
    @Query("select DATE(date),COUNT(*) from ServicesScheduling where    date>=  ?1 and  date <=  CURRENT_DATE   group by DATE(date)")
    List<Date> getSchedulingLastSevenDays(LocalDate date);
    @Query("select count(*) from ServicesScheduling where status='done'  and  date>=  ?1 and  date <=  CURRENT_DATE")
    Integer getCountScheduling(LocalDate date);

    // ---------------------------------------------------------------
    @Query(value = "select cep from scheduling_status as ss join scheduling as s on s.id_scheduling = ss.scheduling_id_scheduling join user on customer_id_user = id_user where service_status='done' group by user.cep", nativeQuery=true)
    List<String> getCepGroup();

    @Query(value = "select count(*) from scheduling_status as ss join scheduling as s on s.id_scheduling = ss.scheduling_id_scheduling join user on customer_id_user = id_user where service_status='done' group by user.cep", nativeQuery=true)
    List<Integer> getCountIntegerGroupCep();


}