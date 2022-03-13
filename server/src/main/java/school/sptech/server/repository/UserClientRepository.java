package school.sptech.server.repository;

import org.springframework.data.repository.CrudRepository;
import school.sptech.server.model.UserCliente;

import java.util.List;

public interface UserClientRepository extends CrudRepository<UserCliente,Integer> {

        List<UserCliente> findAll();

        UserCliente findByid(int id);

        void delete (UserCliente usuario);

        UserCliente save(UserCliente usuario);



}
