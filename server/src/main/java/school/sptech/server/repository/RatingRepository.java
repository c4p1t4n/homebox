package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import school.sptech.server.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query("select AVG(rat.rating) from Rating rat where rat.accomplishedService.scheduling.service.worker.id = ?1 GROUP BY rat.accomplishedService.scheduling.service.worker.id")
    Double getAvgRatingForWorker(Integer idWorker);

}
