package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.Category;
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
    List<User> users = new ArrayList<>();
    @Autowired
    private UserWorkerRepository dbRepositoryWorker;

    @PostMapping("/customer")
    public ResponseEntity registerUserCustomer(@RequestBody UserCustomer newUser) {

        if (newUser.getType().equals("customer")) {
            dbRepositoryCustomer.save((UserCustomer) newUser);
        } else {
            return ResponseEntity.status(403).build();
        }
        newUser.setAuthenticated(Boolean.FALSE);
        users.add(newUser);
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
                dbRepositoryWorker.save(newUser);
            } else {
                return ResponseEntity.status(403).build();
            }
            newUser.setAuthenticated(Boolean.FALSE);
            users.add(newUser);
            return ResponseEntity.status(201).build();
        } catch (NullPointerException npe) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/worker")
    public ResponseEntity<List<UserWorker>> getUserWorker() {
        List<UserWorker> users = dbRepositoryWorker.findAll().stream()
                .filter(user -> user.getType().equals("worker"))
                .collect(Collectors.toList());
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(204).build();
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(204).build();
    }

    @GetMapping("/login/{userLogin}/{userPassword}")
    public ResponseEntity getLoginUser(@PathVariable String userLogin, @PathVariable String userPassword) {
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
        for (User user : users) {
            if (user.getEmail().equals(userLogin) & user.getAuthenticated().equals(Boolean.TRUE)) {
                user.setAuthenticated(Boolean.FALSE);
                return ResponseEntity.status(200).build();
            }
            if (user.getEmail().equals(userLogin) & user.getAuthenticated().equals(Boolean.FALSE)) {
                return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("worker/report")
    public ResponseEntity getReport() {
        String report = "";

        List<UserWorker> list = dbRepositoryWorker.findAll();
        for(var user : list) {
            report += user.getId_user()+","+user.getName()+","+user.getEmail()+","+user.getPassword()+","+user.getCpf()+
                    ","+user.getToken()+","+user.getType()+","+user.getPicture()+","+user.getCep()+"\r\n";
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                //.header("content-length", "9999999999")
                .header("content-disposition", "filename=\"userWorker.csv\"")
                .body(report);
    }
}
