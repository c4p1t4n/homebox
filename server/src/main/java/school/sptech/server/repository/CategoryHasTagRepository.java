package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.CategoryHasTag;

public interface CategoryHasTagRepository extends JpaRepository<CategoryHasTag, Integer> {

}
