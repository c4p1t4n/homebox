package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCustomer;
import school.sptech.server.model.UserWorker;
import school.sptech.server.repository.UserCustomerRepository;
import school.sptech.server.repository.UserWorkerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserCustomerRepository dbRepositoryCustomer;

    @Autowired
    private UserWorkerRepository dbRepositoryWorker;

    @PostMapping("/customer")
    public ResponseEntity registerUserCustomer(@RequestBody UserCustomer newUser) {

        if (newUser.getType().equals("customer")) {
            newUser.setAuthenticated('n');
            dbRepositoryCustomer.save(newUser);
        } else {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.status(201).build();

    }

    @GetMapping("/customer")
    public ResponseEntity<List<UserCustomer>> getUserCustomer() {
        List<UserCustomer> users = dbRepositoryCustomer.findAll().stream()
                .filter(user -> user.getType().equals("customer"))
                .collect(Collectors.toList());
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(204).build();
    }

    @PostMapping("/worker")
    public ResponseEntity registerUserWorker(@RequestBody UserWorker newUser) {
        try {

            if (newUser.getType().equals("worker")) {
                newUser.setAuthenticated('n');
                dbRepositoryWorker.save(newUser);
            } else {
                return ResponseEntity.status(403).build();
            }

            return ResponseEntity.status(201).build();
        } catch (NullPointerException npe) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/worker")
    public ResponseEntity getUserWorker() {
        // status(400)solucao paliativa pois estava dando erro quando a lista estava vazia
        if(dbRepositoryWorker.findAll().isEmpty()){
            return ResponseEntity.status(400).build();
        }
        List<UserWorker> users = dbRepositoryWorker.findAll().stream()
                .filter(user -> user.getType().equals("worker"))
                .collect(Collectors.toList());
        return  ResponseEntity.status(200).body(users);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        List<User> users = new ArrayList<>();
        List<UserCustomer> userCustomers = dbRepositoryCustomer.findAll();
        List<UserWorker> userWorkers = dbRepositoryWorker.findAll();
        userCustomers.stream().filter(user -> user.getType().equals("customer")).forEach(users::add);
        userWorkers.stream().filter(user -> user.getType().equals("worker")).forEach(users::add);

        // status(400) solucao paliativa pois estava dando erro quando a lista estava vazia
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(400).build();
    }

    @GetMapping("/login/{userLogin}/{userPassword}")
    public ResponseEntity getLoginUser(@PathVariable String userLogin, @PathVariable String userPassword) {
        List<User> users = new ArrayList<>();
        List<UserCustomer> userCustomers = dbRepositoryCustomer.findAll();
        List<UserWorker> userWorkers = dbRepositoryWorker.findAll();
        users.addAll(userCustomers);
        users.addAll(userWorkers);


        for (User user : users) {
            if (user.getEmail().equals(userLogin) && user.getPassword().equals(userPassword)) {
                user.login(userLogin, userPassword);
                return ResponseEntity.status(200).build();
            }

        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/logoff/{userLogin}")
    public ResponseEntity logoffUser(@PathVariable String userLogin) {

        List<UserCustomer> usersCostumers = dbRepositoryCustomer.findAll();
        List<UserWorker> usersWorkers = dbRepositoryWorker.findAll();

        for (User user : usersCostumers) {
            if (user.getEmail().equals(userLogin) & user.getAuthenticated().equals('s')) {
                user.setAuthenticated('n');
                return ResponseEntity.status(200).build();
            }
            if (user.getEmail().equals(userLogin) & user.getAuthenticated().equals('n')) {
                return ResponseEntity.status(403).build();
            }
        }

        for (User user : usersWorkers) {
            if (user.getEmail().equals(userLogin) & user.getAuthenticated().equals('s')) {
                user.setAuthenticated('n');
                return ResponseEntity.status(200).build();
            }
            if (user.getEmail().equals(userLogin) & user.getAuthenticated().equals('n')) {
                return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.status(400).build();
    }

}
