package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.server.model.Search;
import school.sptech.server.model.UserHasSearch;
import school.sptech.server.repository.SearchRepository;
import school.sptech.server.repository.UserHasSearchRepository;
import school.sptech.server.repository.UserRepository;
import school.sptech.server.request.UserSearchRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import school.sptech.server.service.HashTable;
import school.sptech.server.service.PilhaLigada;
import school.sptech.server.service.PilhaObj;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchRepository dbRepositorySearch;

    @Autowired
    private UserHasSearchRepository dbRepositoryUserHasSearch;

    @Autowired
    private UserRepository dbRepositoryUser;
    PilhaObj<String> lastSearchs = new PilhaObj<>(5);
    PilhaLigada lastSearchPL = new PilhaLigada();
//    HashTable hashTable1 = new HashTable(5);

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Object> postSearchPerUser(@RequestBody UserSearchRequest searchReq) {
        Search search = dbRepositorySearch.findByValue(searchReq.getValue());

        if (Objects.isNull(search)) {
            search = dbRepositorySearch.save(new Search(searchReq.getValue()));
        }
        lastSearchs.push(search.getValue());

        dbRepositoryUserHasSearch.save(
                new UserHasSearch(search, dbRepositoryUser.findById(searchReq.getIdUser()).get(), LocalDate.now()));
        return ResponseEntity.status(201).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Search>> getAllSearch() {
        List<Search> searchList = dbRepositorySearch.findAll();
        if (searchList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(searchList);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{idSearch}")
    public ResponseEntity<Optional<Search>> getSearchById(@PathVariable Integer idSearch) {
        if (dbRepositorySearch.existsById(idSearch)) {
            return ResponseEntity.status(200).body(dbRepositorySearch.findById(idSearch));
        }
        return ResponseEntity.status(404).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/user/{idUsuario}")
    public ResponseEntity<List<Search>> getSearchPerUser(@PathVariable Integer idUsuario) {
        if (dbRepositoryUser.existsById(idUsuario)) {
            List<Search> list = dbRepositoryUserHasSearch.findAllByUserId(idUsuario);
            if (list.isEmpty()) {
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(200).body(list);
        }
        return ResponseEntity.status(404).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{idSearch}")
    public ResponseEntity<Object> deleteSearch(@PathVariable Integer idSearch) {
        if (dbRepositorySearch.existsById(idSearch)) {
            dbRepositorySearch.deleteById(idSearch);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/last/search")
    public ResponseEntity<List<String>> getLastSearchs() {
        List<String> list = new ArrayList<>(5);
        while (!lastSearchPL.isEmpty()) {
            list.add(lastSearchs.pop());
        }
        lastSearchs.setTopo(4);
        if (list.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(list);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("pilha-ligada/last/search")
    public ResponseEntity<List<String>> getLastSearchUsingPilhaLigada() {
        List<String> list = new ArrayList<>(5);
        int i = 0;
        while (!lastSearchPL.isEmpty()) {
            list.add(lastSearchPL.peek());
            lastSearchPL.pop(list.get(i));
            i++;
        }
        lastSearchPL.setTopo(4);
        if (list.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(list);
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping("pilha-ligada1/last/search")
//    public ResponseEntity<HashTable> getLastSearchUsingPilhaLigada1() {
////        List<String> list = new ArrayList<>(5);
//        HashTable hashTable1 = new HashTable(5);
//        int i = 0;
//        while (!lastSearchPL.isEmpty()) {
//            hashTable1.insere(i, lastSearchPL.peek());
//            lastSearchPL.pop(lastSearchPL.peek());
//            i++;
//        }
//        lastSearchPL.setTopo(4);
//        if (lastSearchPL.isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }
//        return ResponseEntity.status(200).body(hashTable1);
//    }

}
