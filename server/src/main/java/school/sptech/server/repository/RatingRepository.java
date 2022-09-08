package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import school.sptech.server.model.Rating;

import java.time.LocalDate;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query("select AVG(rat.rating) from Rating rat where rat.accomplishedService.scheduling.service.worker.id = ?1 GROUP BY rat.accomplishedService.scheduling.service.worker.id")
    Double getAvgRatingForWorker(Integer idWorker);

    @Query("select  AVG(rat.rating) from Rating rat where rat.accomplishedService.scheduling.service.worker.id = ?1  and rat.accomplishedService.serviceDate  BETWEEN  DATE(?2)  and DATE(CURRENT_DATE)  GROUP BY rat.accomplishedService.scheduling.service.worker.id,rat.accomplishedService.serviceDate ORDER BY  rat.accomplishedService.serviceDate ")
    List<Double> getAvgRatingForWorkerLastSevenDays(Integer idWorker,LocalDate date);


    boolean existsByAccomplishedServiceSchedulingIdScheduling(Integer id);

}
