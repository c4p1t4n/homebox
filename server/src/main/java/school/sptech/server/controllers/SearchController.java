package school.sptech.server.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.server.model.Search;
import school.sptech.server.model.SearchUser;
import school.sptech.server.repository.SearchRepository;
import school.sptech.server.repository.SearchUserRepository;
import school.sptech.server.repository.UserRepository;
import school.sptech.server.request.UserSearchRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchRepository dbRepositorySearch;

    @Autowired
    private SearchUserRepository dbRepositorySearchUser;

    @Autowired
    private UserRepository dbRepositoryCustomer;

    @PostMapping
    public ResponseEntity<Object> postSearchPerUser(@RequestBody @NotNull UserSearchRequest searchReq) {
        Search search = dbRepositorySearch.findByValue(searchReq.getValue());
        if (Objects.isNull(search)) {
            search = dbRepositorySearch.save(new Search(searchReq.getValue()));
        }
        dbRepositorySearchUser.save(new SearchUser(search.getIdSearch(), searchReq.getIdUser(), LocalDate.now()));
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Search>> getAllSearch() {
        List<Search> searchList = dbRepositorySearch.findAll();
        if (searchList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(searchList);
    }

    @GetMapping("/{idSearch}")
    public ResponseEntity<Optional<Search>> getSearchById(@PathVariable Integer idSearch) {
        if (dbRepositorySearch.existsById(idSearch)) {
            return ResponseEntity.status(200).body(dbRepositorySearch.findById(idSearch));
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<Search>> getSearchPerUser(@PathVariable Integer idUsuario){
        if (dbRepositoryCustomer.existsById(idUsuario)){
            List<Search> list = dbRepositorySearchUser.findAllByFkUser(idUsuario);
            if (list.isEmpty()){
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(200).body(list);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{idSearch}")
    public ResponseEntity<Object> deleteSearch(@PathVariable Integer idSearch) {
        if (dbRepositorySearch.existsById(idSearch)) {
            dbRepositorySearch.deleteById(idSearch);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }




}

