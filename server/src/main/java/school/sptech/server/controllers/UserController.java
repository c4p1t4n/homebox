package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.server.model.Category;
import school.sptech.server.model.User;
import school.sptech.server.repository.CategoryRepository;
import school.sptech.server.repository.RatingRepository;
import school.sptech.server.repository.ServiceRepository;
import school.sptech.server.response.UserSearchQueryResult;
import school.sptech.server.service.UserService;

import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService dbUserService;

    @Autowired
    private CategoryRepository dbRepositoryCategory;

    @Autowired
    private ServiceRepository dbRepositoryService;
    @Autowired
    private RatingRepository dbRepositoryRating;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/customer")
    public ResponseEntity<Object> registerUserCustomer(@RequestBody User newUser) {

        if (!newUser.getType().equals("customer")) {
            return status(400).build();
        }
        newUser.setAuthenticated('n');
        User user = dbUserService.saveUser(newUser);

        return status(201).body(user);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/customer")
    public ResponseEntity<List<User>> getUserCustomer() {
        return !dbUserService.getAllCustomer().isEmpty()
                ? status(200).body(dbUserService.getAllCustomer())
                : status(204).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/worker")
    public ResponseEntity<Object> registerUserWorker(@RequestBody User newUser) {
        try {

            if (!newUser.getType().equals("worker")) {
                return status(400).build();
            }
            newUser.setAuthenticated('n');
            User user = dbUserService.saveUser(newUser);

            return status(201).body(user);

        } catch (NullPointerException npe) {
            return status(400).build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/worker")
    public ResponseEntity<List<User>> getUserWorker() {

        return !dbUserService.getAllWorkers().isEmpty() ? status(200).body(dbUserService.getAllWorkers())
                : status(204).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        return !dbUserService.getAll().isEmpty() ? status(200).body(dbUserService.getAll())
                : status(204).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/login/{userLogin}/{userPassword}")
    public ResponseEntity<Object> getLoginUser(@PathVariable String userLogin, @PathVariable String userPassword) {
        List<User> users = dbUserService.getAll();

        for (User user : users) {
            if (user.getEmail().equals(userLogin) && user.getPassword().equals(userPassword)) {
                user.login(userLogin, userPassword);
                return status(200).build();
            }

        }
        return status(401).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/logoff/{userLogin}")
    public ResponseEntity<Object> logoffUser(@PathVariable String userLogin) {

        List<User> users = dbUserService.getAll();

        for (User user : users) {
            if (user.getEmail().equals(userLogin) & user.getAuthenticated().equals('s')) {
                user.setAuthenticated('n');
                return status(200).build();
            }
            if (user.getEmail().equals(userLogin) & user.getAuthenticated().equals('n')) {
                return status(403).build();
            }
        }

        return status(400).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("worker/report")
    public ResponseEntity<String> getReport() {
        String report = "";

        List<User> list = dbUserService.getAllWorkers();
        for (var user : list) {
            report += user.getId() + "," + user.getName() + "," + user.getEmail() + "," + user.getPassword() + ","
                    + user.getCpf() +
                    "," + user.getToken() + "," + user.getType() + "," + user.getPicture() + "," + user.getCep()
                    + "\r\n";
        }

        return status(200)
                .header("content-type", "text/csv")
                // .header("content-length", "9999999999")
                .header("content-disposition", "filename=\"userWorker.csv\"")
                .body(report);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/worker/categories/{id}")
    public ResponseEntity<List<Category>> getWorkerCategories(@PathVariable Integer id) {
        List<Category> categories = dbUserService.getWorkerCategories(id);

        return categories.isEmpty() ? status(204).build() : status(200).body(categories);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/search/{value}")
    public ResponseEntity<List<UserSearchQueryResult>> search(@PathVariable String value) {
        ResponseEntity<List<User>> response = getWorkersByCategory(value);
        List<UserSearchQueryResult> users;

        if (response.getStatusCodeValue() == 404 || response.getStatusCodeValue() == 204) {
            users = dbRepositoryService.searchUsers(value).stream()
                    .map((user) -> new UserSearchQueryResult(user,
                            dbRepositoryRating.getAvgRatingForWorker(user.getId())))
                    .collect(Collectors.toList());

        } else {
            users = response
                    .getBody()
                    .stream()
                    .map((user) -> new UserSearchQueryResult(user,
                            dbRepositoryRating.getAvgRatingForWorker(user.getId())))
                    .collect(Collectors.toList());
        }

        if (users.isEmpty()) {
            return status(204).build();
        }
        return status(200).body(users);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/category/{value}")
    public ResponseEntity<List<User>> getWorkersByCategory(@PathVariable String value) {
        if (!dbRepositoryCategory.existsByNameContainsIgnoreCase(value)) {
            return status(404).build();
        }

        List<User> users = dbRepositoryService.findByCategoryNameContainsIgnoreCase(value);

        if (users.isEmpty()) {
            return status(204).build();
        }

        return status(200).body(users);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/avg-rating/{idUser}")
    public ResponseEntity<Double> getAvgRating(@PathVariable Integer idUser) {
        if (!dbUserService.existsById(idUser)) {
            return status(404).build();
        }
        Double rating = dbRepositoryRating.getAvgRatingForWorker(idUser);

        if (rating == null) {
            return status(204).build();
        }

        return status(200).body(rating);
    }
}
