package school.sptech.server.repository;

import org.springframework.data.repository.CrudRepository;
import school.sptech.server.model.Staff;

import java.util.List;

public interface StaffRepository extends CrudRepository<Staff, Integer> {

    List<Staff> findAll();

    Staff findByid(int id);

    void delete(Staff usuario);

    Staff save(Staff usuario);

}
