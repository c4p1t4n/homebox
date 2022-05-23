package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Media;

public interface MediaRepository extends JpaRepository<Media, Integer> {
    Media findMediaByIdMedia(Integer fkMedia);
}
