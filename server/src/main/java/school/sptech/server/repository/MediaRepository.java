package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.server.model.Media;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    Boolean existsByPath(String fileName);

}
