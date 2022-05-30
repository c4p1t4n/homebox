package school.sptech.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.server.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAll();

    List<Category> findByIdCategory(Integer idCategory);

    List<Category> findByNameIgnoreCase(String name);

    boolean existsByIdCategory(Integer idCategory);

    List<Category> deleteByIdCategory(Integer idCategory);

    Integer findIdCategoryByName(String name);

    boolean existsByNameContainsIgnoreCase(String category);
}
