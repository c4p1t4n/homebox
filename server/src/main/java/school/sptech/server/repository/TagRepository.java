package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Override
    boolean existsById(Integer integer);

    boolean existsByValue(String value);

    Tag findByValue(String value);
}
