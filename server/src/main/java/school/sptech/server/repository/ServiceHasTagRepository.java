package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.ServiceHasTag;

public interface ServiceHasTagRepository extends JpaRepository<ServiceHasTag, Integer> {

}
