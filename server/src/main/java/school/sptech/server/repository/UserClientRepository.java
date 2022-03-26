package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.UserCliente;

public interface UserClientRepository extends JpaRepository<UserCliente, Integer> {

}
