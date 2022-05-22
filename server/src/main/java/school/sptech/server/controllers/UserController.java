package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.server.model.Category;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCustomer;
import school.sptech.server.model.UserWorker;
import school.sptech.server.service.UserService;

import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService dbUserService;

    @PostMapping("/customer")
    public ResponseEntity<Object> registerUserCustomer(@RequestBody UserCustomer newUser) {

        if (newUser.getType().equals("customer")) {
            newUser.setAuthenticated('n');
            dbUserService.saveUser(newUser);
        } else {
            return status(403).build();
        }

        return status(201).build();

    }

    @GetMapping("/customer")
    public ResponseEntity<List<User>> getUserCustomer() {
        return !dbUserService.getAllCustomer().isEmpty()
                ? status(200).body(dbUserService.getAllCustomer())
                : status(204).build();
    }

    @PostMapping("/worker")
    public ResponseEntity<Object> registerUserWorker(@RequestBody UserWorker newUser) {
        try {

            if (newUser.getType().equals("worker")) {
                newUser.setAuthenticated('n');
                dbUserService.saveUser(newUser);
            } else {
                return status(403).build();
            }

            return status(201).build();
        } catch (NullPointerException npe) {
            return status(400).build();
        }
    }

    @GetMapping("/worker")
    public ResponseEntity<List<User>> getUserWorker() {

        return !dbUserService.getAllWorkers().isEmpty() ? status(200).body(dbUserService.getAllWorkers())
                : status(204).build();
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        return !dbUserService.getAll().isEmpty() ? status(200).body(dbUserService.getAll())
                : status(204).build();
    }

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

    @GetMapping(value = "/worker/categories/{id}")
    public ResponseEntity<List<Category>> getWorkerCategories(@PathVariable Integer id) {
        List<Category> categories = dbUserService.getWorkerCategories(id);

        return categories.isEmpty() ? status(204).build() : status(200).body(categories);
    }

}
