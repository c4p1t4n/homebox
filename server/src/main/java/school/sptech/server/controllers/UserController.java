package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCustomer;
import school.sptech.server.model.UserWorker;
import school.sptech.server.repository.UserCustomerRepository;
import school.sptech.server.repository.UserWorkerRepository;
import school.sptech.server.request.UserTypeManager;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserCustomerRepository dbRepositoryCustomer;

    @Autowired
    private UserWorkerRepository dbRepositoryWorker;

    @Autowired
    private  UserTypeManager dbServiceUserTypeManager;

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
    public ResponseEntity<List<User>> getUserCustomer() {
        dbServiceUserTypeManager.ResetList();
        List<UserCustomer> users = dbRepositoryCustomer.findAll();
        dbServiceUserTypeManager.addAllUsers(users);
        List<User> allCustomer = dbServiceUserTypeManager.getAllCustomer();
        return !allCustomer.isEmpty() ? ResponseEntity.status(200).body(allCustomer) : ResponseEntity.status(204).build();
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
        dbServiceUserTypeManager.ResetList();
        List<UserCustomer> users = dbRepositoryCustomer.findAll();
        dbServiceUserTypeManager.addAllUsers(users);
        List<User> allWorkers = dbServiceUserTypeManager.getAllWorkers();
        return !allWorkers.isEmpty() ? ResponseEntity.status(200).body(allWorkers): ResponseEntity.status(204).build();
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        dbServiceUserTypeManager.ResetList();

        List<UserCustomer> usersCostumers = dbRepositoryCustomer.findAll();
        dbServiceUserTypeManager.addAllUsers(usersCostumers);

        return !dbServiceUserTypeManager.getAllUsers().isEmpty() ? ResponseEntity.status(200).body(dbServiceUserTypeManager.getAllUsers()) : ResponseEntity.status(204).build();
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

    @GetMapping("Teste")
    public List<User> teste(){
        return dbServiceUserTypeManager.getAllWorkers();

    }

    @GetMapping("worker/report")
    public ResponseEntity getReport() {
        String report = "";

        List<UserWorker> list = dbRepositoryWorker.findAll();
        for (var user : list) {
            report += user.getId_user() + "," + user.getName() + "," + user.getEmail() + "," + user.getPassword() + ","
                    + user.getCpf() +
                    "," + user.getToken() + "," + user.getType() + "," + user.getPicture() + "," + user.getCep()
                    + "\r\n";
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                // .header("content-length", "9999999999")
                .header("content-disposition", "filename=\"userWorker.csv\"")
                .body(report);
    }
}
