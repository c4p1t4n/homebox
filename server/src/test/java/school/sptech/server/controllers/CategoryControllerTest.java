package school.sptech.server.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.server.model.Category;
import school.sptech.server.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CategoryController.class})
class CategoryControllerTest {

    @Autowired
    CategoryController categoryController;

    @MockBean
    private CategoryRepository dbRepositoryCategory;

    @Test
    @DisplayName("Sem categorias deveria retornar 204 SEM corpo")
    void getCategoriesEmpty() {
        when(dbRepositoryCategory.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Category>> response = categoryController.getCategories();

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("Com categorias deveria retornar 200 e COM corpo")
    void getCategoriesWithBody(){
        Category category1 = mock(Category.class);
        Category category2 = mock(Category.class);
        List<Category> mockList = List.of(category1, category2);

        when(dbRepositoryCategory.findAll()).thenReturn(mockList);

        ResponseEntity<List<Category>> response = categoryController.getCategories();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockList, response.getBody());
    }

    @Test
    @DisplayName("No cadastro de uma categoria, se der certo, deve retornar 201 SEM corpo")
    void postCategoryWithResult() {
        Category category1 = mock(Category.class);
        when(dbRepositoryCategory.save(category1)).thenReturn(new Category());

        ResponseEntity<Object> response = categoryController.postCategory(category1);

        assertEquals(201, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("No cadastro de uma categoria, se der errado, deve retornar 404 SEM corpo")
    void postCategoryWithoutResult() {
        Category category1 = null;
        when(dbRepositoryCategory.save(category1)).thenReturn(null);

        ResponseEntity<Object> response = categoryController.postCategory(category1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("No filtro por id, se encontrar, deve retornar 200 COM corpo")
    void filterByIdWithResult() {
        Category category1 = new Category(1, "Elétrica");
        Integer idTest = 1;
        when(dbRepositoryCategory.existsById(idTest)).thenReturn(true);
        when(dbRepositoryCategory.findById(idTest)).thenReturn(Optional.of(category1));

        ResponseEntity<Category> response = categoryController.getCategoryById(idTest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(category1, response.getBody());
    }

    @Test
    @DisplayName("No filtro por id, se não encontrar, deve retornar 404 sem corpo")
    void filterByIdWithouResult() {
        Integer idTest = 1;
        when(dbRepositoryCategory.findByIdCategory(idTest)).thenReturn(new ArrayList<>());

        ResponseEntity<Category> response = categoryController.getCategoryById(idTest);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("No filtro por nome, se encontrar, deve retornar 200 COM corpo")
    void filterByNameWithResult() {
        Category category1 = mock(Category.class);
        Category category2 = mock(Category.class);
        List<Category> mockList = List.of(category1, category2);

        String filter = "Elétrica";
        when(dbRepositoryCategory.findByNameIgnoreCase(filter)).thenReturn(mockList);

        ResponseEntity<List<Category>> response = categoryController.getCategoryByName(filter);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockList.size(), response.getBody().size());
        assertEquals(category1, response.getBody().get(0));
    }

    @Test
    @DisplayName("No filtro por nome, se não encontrar, deve retornar 204 sem corpo")
    void filterByNameWithouResult() {
        String filter = "Elétrica";
        when(dbRepositoryCategory.findByNameIgnoreCase(filter)).thenReturn(new ArrayList<>());

        ResponseEntity<List<Category>> response = categoryController.getCategoryByName(filter);

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("No delete por id, se encontrar, deve retornar 200 SEM corpo")
    void DeleteByIdWithResult() {
        Integer idTest = 1;
        when(dbRepositoryCategory.existsByIdCategory(idTest)).thenReturn(true);
        when(dbRepositoryCategory.findByIdCategory(idTest)).thenReturn(new ArrayList<>());

        ResponseEntity<Void> response = categoryController.deleteCategory(idTest);

        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("No delete por id, se não encontrar, deve retornar 404 sem corpo")
    void DeleteByIdWithouResult() {
        Integer idTest = 1;
        when(dbRepositoryCategory.findByIdCategory(idTest)).thenReturn(new ArrayList<>());

        ResponseEntity<Void> response = categoryController.deleteCategory(idTest);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("Na busca de um id por nome, se encontrar, deve retornar 200 COM corpo")
    void getIdByNameWithResult() {
        Category category1 = new Category(1, "Elétrica");
        String name = "Elétrica";
        when(dbRepositoryCategory.findIdCategoryByName(name)).thenReturn(category1.getIdCategory());

        ResponseEntity response = categoryController.getMethodName(name);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(category1.getIdCategory(), response.getBody());
    }

    @Test
    @DisplayName("Na busca de um id por nome, se não encontrar, deve retornar 404 SEM corpo")
    void getIdByNameWithouResult() {
        String name = "Elétrica";
        when(dbRepositoryCategory.findIdCategoryByName(name)).thenReturn(null);

        ResponseEntity response = categoryController.getMethodName(name);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

}