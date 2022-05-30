package school.sptech.server.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.server.model.Search;
import school.sptech.server.repository.SearchRepository;
import school.sptech.server.repository.SearchUserRepository;
import school.sptech.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {SearchController.class})
class SearchControllerTest {

    @Autowired
    SearchController searchController;

    @MockBean
    private SearchRepository dbRepositorySearch;

    @MockBean
    private SearchUserRepository dbRepositorySearchUser;

    @MockBean
    private UserRepository dbRepositoryCustomer;

    @Test
    @DisplayName("Se não existem pesquisas, deveria retornar 204 SEM corpo")
    void testGetListOfAllSearchIsEmpty() {
        when(dbRepositorySearch.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Search>> response = searchController.getAllSearch();

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("Se existem pesquisas deveria retornar 200 e COM corpo")
    void testGetListOfAllSearchWithBody(){
        Search search1 = mock(Search.class);
        Search search2 = mock(Search.class);
        List<Search> mockList = List.of(search1, search2);

        when(dbRepositorySearch.findAll()).thenReturn(mockList);

        ResponseEntity<List<Search>> response = searchController.getAllSearch();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(mockList, response.getBody());
    }

    @Test
    @DisplayName("No delete por id, se encontrar, deve retornar 200 SEM corpo")
    void testDeleteByIdWithResult() {
        Integer idTest = 1;
        when(dbRepositorySearch.existsById(idTest)).thenReturn(true);
        when(dbRepositorySearch.findByIdSearch(idTest)).thenReturn(new ArrayList<>());

        ResponseEntity<Object> response = searchController.deleteSearch(idTest);

        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("No delete por id, se não encontrar, deve retornar 404 sem corpo")
    void testDeleteByIdWithouResult() {
        Integer idTest = 1;
        when(dbRepositorySearch.findByIdSearch(idTest)).thenReturn(new ArrayList<>());

        ResponseEntity<Object> response = searchController.deleteSearch(idTest);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

}