package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.MsgHasMedia;
import school.sptech.server.service.MsgHasMediaId;

public interface MsgHasMediaRepository extends JpaRepository<MsgHasMedia, MsgHasMediaId> {
}
