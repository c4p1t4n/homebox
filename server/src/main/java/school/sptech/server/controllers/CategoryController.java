package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.server.model.Category;
import school.sptech.server.repository.CategoryRepository;


import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository dbRepositoryCategory;

    @GetMapping("/report")
    public ResponseEntity getReport() {
        String report = "";

        List<Category> list = dbRepositoryCategory.findAll();
        for(var categ : list) {
            report += categ.getIdCategory()+","+categ.getName()+"\r\n";
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                //.header("content-length", "9999999999")
                .header("content-disposition", "filename=\"category.csv\"")
                .body(report);
    }

}

