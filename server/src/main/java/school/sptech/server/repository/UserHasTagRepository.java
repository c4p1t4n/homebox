package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Tag;
import school.sptech.server.model.UserHasTag;

public interface UserHasTagRepository extends JpaRepository<UserHasTag, Integer> {
    Integer countByTag(Tag tag);
}
