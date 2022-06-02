package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.server.client.CalcDistClient;
import school.sptech.server.model.Category;
import school.sptech.server.model.User;
import school.sptech.server.service.ExportTxt;
import school.sptech.server.service.FilaObj;
import school.sptech.server.request.LoginRequest;
import school.sptech.server.request.UserIdRequest;
import school.sptech.server.repository.CategoryRepository;
import school.sptech.server.repository.RatingRepository;
import school.sptech.server.repository.ServiceRepository;
import school.sptech.server.response.UserSearchQueryResult;
import school.sptech.server.service.UserService;

import static org.springframework.http.ResponseEntity.status;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService dbServiceUser;

    @Autowired
    private CategoryRepository dbRepositoryCategory;
    @Autowired
    private CalcDistClient distClient;

    @Autowired
    private ServiceRepository dbRepositoryService;
    @Autowired
    private RatingRepository dbRepositoryRating;

    @Autowired
    private CategoryRepository categoryRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/customer")
    public ResponseEntity<Object> registerUserCustomer(@RequestBody User newUser) {

        if (!newUser.getType().equals("customer")) {
            return status(400).build();
        }
        try {
            newUser.setPassword(dbServiceUser.encriptPassword(newUser.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        newUser.setAuthenticated('n');
        User user = dbServiceUser.saveUser(newUser);

        return status(201).body(user);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/customer")
    public ResponseEntity<List<User>> getUserCustomer() {
        return !dbServiceUser.getAllCustomer().isEmpty()
                ? status(200).body(dbServiceUser.getAllCustomer())
                : status(204).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/worker")
    public ResponseEntity<Object> registerUserWorker(@RequestBody User newUser) {
        try {

            if (!newUser.getType().equals("worker")) {
                return status(400).build();
            }
            try {
                newUser.setPassword(dbServiceUser.encriptPassword(newUser.getPassword()));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            newUser.setAuthenticated('n');
            User user = dbServiceUser.saveUser(newUser);

            return status(201).body(user);

        } catch (

        NullPointerException npe) {
            return status(400).build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/worker")
    public ResponseEntity<List<User>> getUserWorker() {

        return !dbServiceUser.getAllWorkers().isEmpty() ? status(200).body(dbServiceUser.getAllWorkers())
                : status(204).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        return !dbServiceUser.getAll().isEmpty() ? status(200).body(dbServiceUser.getAll())
                : status(204).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<User> LoginUser(@RequestBody LoginRequest loginCredentials) {
        if (!dbServiceUser.existsByEmail(loginCredentials.getEmail())) {
            return status(404).build();
        }

        User user = null;

        try {
            user = dbServiceUser.login(loginCredentials.getEmail(), loginCredentials.getPassword());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(user)) {
            return status(400).build();
        }

        return status(200).body(user);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/logoff/{userLogin}")
    public ResponseEntity<Object> logoffUser(@PathVariable String userLogin) {

        List<User> users = dbServiceUser.getAll();

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
    public ResponseEntity<StringBuilder> getReport() throws IOException {

        ExportTxt ex = new ExportTxt();

        List<User> users = dbServiceUser.getAll();
        List<Category> categories = categoryRepository.findAll();

        return status(200)
                .header("content-type", "text/txt")
                // .header("content-length", "9999999999")
                .header("content-disposition", "filename=\"report.txt\"")
                .body(ex.gravaArquivoTxt(users, categories, "report.txt"));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/worker/categories/{id}")
    public ResponseEntity<List<Category>> getWorkerCategories(@PathVariable Integer id) {
        List<Category> categories = dbServiceUser.getWorkerCategories(id);

        return categories.isEmpty() ? status(204).build() : status(200).body(categories);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/search/{value}")
    public ResponseEntity<List<UserSearchQueryResult>> search(@PathVariable String value,
            @RequestBody UserIdRequest requestId) {
        ResponseEntity<List<UserSearchQueryResult>> response = getWorkersByCategory(value);
        List<UserSearchQueryResult> users;

        if (Objects.isNull(requestId.getId()) || !dbServiceUser.existsById(requestId.getId())) {
            return status(404).build();
        }

        if (response.getStatusCodeValue() == 404 || response.getStatusCodeValue() == 204) {
            users = dbRepositoryService.searchUsers(value).stream()
                    .map((user) -> new UserSearchQueryResult(user,
                            dbRepositoryRating.getAvgRatingForWorker(user.getId()),
                            getDist(user.getCep(), dbServiceUser.findById(requestId.getId()).get().getCep()).getBody(),
                            getWorkerCategories(user.getId()).getBody().get(0).getName()))
                    .collect(Collectors.toList());

        } else {
            users = response
                    .getBody()
                    .stream()
                    .map((item) -> new UserSearchQueryResult(item.getUser(),
                            dbRepositoryRating.getAvgRatingForWorker(item.getUser().getId()),
                            getDist(item.getUser().getCep(), dbServiceUser.findById(requestId.getId()).get().getCep())
                                    .getBody(),
                            item.getCategory()))
                    .collect(Collectors.toList());
        }

        if (users.isEmpty()) {
            return status(204).build();
        }
        return status(200).body(users);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/category/{value}")
    public ResponseEntity<List<UserSearchQueryResult>> getWorkersByCategory(@PathVariable String value) {
        if (!dbRepositoryCategory.existsByNameContainsIgnoreCase(value)) {
            return status(404).build();
        }

        List<UserSearchQueryResult> users = dbRepositoryService.findByCategoryNameContainsIgnoreCase(value);

        if (users.isEmpty()) {
            return status(204).build();
        }

        return status(200).body(users);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/avg-rating/{idUser}")
    public ResponseEntity<Double> getAvgRating(@PathVariable Integer idUser) {
        if (!dbServiceUser.existsById(idUser)) {
            return status(404).build();
        }
        Double rating = dbRepositoryRating.getAvgRatingForWorker(idUser);

        if (rating == null) {
            return status(204).build();
        }

        return status(200).body(rating);
    }

    @GetMapping("/distance/cep1/cep2")
    public ResponseEntity<Double> getDist(@PathVariable String cep1,
            @PathVariable String cep2) {

        ResponseEntity<Double> dist = distClient.getDist(cep1, cep2);
        if (Objects.isNull(dist.getBody())) {
            return status(400).build();
        }
        return status(200).body(dist.getBody());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/recomendation/{id}")
    public ResponseEntity<List<UserSearchQueryResult>> getRecomendation(@PathVariable Integer id) {

        if (Objects.isNull(id) || !dbServiceUser.existsById(id)) {
            return status(404).build();
        }

        List<UserSearchQueryResult> users = dbServiceUser.get3Workers().stream()
                .map((user) -> new UserSearchQueryResult(user,
                        dbRepositoryRating.getAvgRatingForWorker(user.getId()),
                        getDist(user.getCep(), dbServiceUser.findById(id).get().getCep()).getBody(),
                        getWorkerCategories(user.getId()).getBody().get(0)))
                .collect(Collectors.toList());

        return status(200).body(users);
    }

    @PatchMapping(value = "/report", consumes = "text/txt; charset: utf-8")
    public ResponseEntity<Object> importUsers(@RequestBody byte[] report) throws IOException {
        String document;
        try {
            document = new String(report, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(400).body("Codificação nao suportada");
        }
        PrintWriter writer = new PrintWriter("ImportReport.txt", "UTF-8");
        writer.println(document);
        writer.close();
        ExportTxt ex = new ExportTxt();
        FilaObj<Object> fila = ex.lerArquivoTxt("ImportReport.txt");
        while (!fila.isEmpty()) {
            if (fila.peek().getClass().toString().equals("class school.sptech.server.model.User")) {
                dbServiceUser.saveUser((User) fila.poll());
            }
            if (fila.peek().getClass().toString().equals("class school.sptech.server.model.Category")) {
                categoryRepository.save((Category) fila.poll());
            }

        }

        return ResponseEntity.status(200).build();
    }
}
