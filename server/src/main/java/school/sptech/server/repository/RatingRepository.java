package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.server.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
