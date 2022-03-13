package school.sptech.server.repository;

import org.springframework.data.repository.CrudRepository;
import school.sptech.server.model.UserPrestador;

import java.util.List;

public interface UserPrestadorRepository extends CrudRepository<UserPrestador,Integer> {
    List<UserPrestador> findAll();

    UserPrestador findByid(int id);

    void delete (UserPrestador usuario);

    UserPrestador save(UserPrestador usuario);




}
