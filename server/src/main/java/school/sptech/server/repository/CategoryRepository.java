package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAll();

    List<Category> findByIdCategory(Integer idCategory);
    List<Category> findByNameIgnoreCase(String name);

    boolean existsByIdCategory(Integer idCategory);
    boolean existsByNameIgnoreCase(String name);
    List<Category> deleteByIdCategory(Integer idCategory);
}
