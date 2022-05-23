package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.Category;
import school.sptech.server.repository.CategoryRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository dbRepositoryCategory;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categoryList = dbRepositoryCategory.findAll();

        if (categoryList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(categoryList);
    }

    @PostMapping
    public ResponseEntity<Object> postCategory(@RequestBody @Valid Category newCategory) {
        dbRepositoryCategory.save(newCategory);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<List<Category>> getCategoryById(@PathVariable Integer idCategory) {
        List<Category> categoryList = dbRepositoryCategory.findByIdCategory(idCategory);

        if (!dbRepositoryCategory.existsByIdCategory(idCategory)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(categoryList);
    }

    @GetMapping("/filtro/{name}")
    public ResponseEntity<List<Category>> getCategoryByName(@PathVariable String name) {
        List<Category> categoryList = dbRepositoryCategory.findByNameIgnoreCase(name);

        if (categoryList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(categoryList);
    }

    @Transactional
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer idCategory) {
        if (dbRepositoryCategory.existsByIdCategory(idCategory)) {
            dbRepositoryCategory.deleteByIdCategory(idCategory);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/report")
    public ResponseEntity<Object> getReport() {
        String report = "";

        List<Category> list = dbRepositoryCategory.findAll();
        for (var categ : list) {
            report += categ.getIdCategory() + "," + categ.getName() + "\r\n";
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                // .header("content-length", "9999999999")
                .header("content-disposition", "filename=\"category.csv\"")
                .body(report);
    }

    @GetMapping(value = "/id/{name}")
    public ResponseEntity<Integer> getMethodName(@PathVariable String name) {

        Integer categoryId = dbRepositoryCategory.findIdCategoryByName(name);

        if (Objects.isNull(categoryId)) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(categoryId);
    }

}
