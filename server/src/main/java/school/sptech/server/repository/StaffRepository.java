package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
