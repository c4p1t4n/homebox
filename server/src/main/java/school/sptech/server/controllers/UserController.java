package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.server.model.Category;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCustomer;
import school.sptech.server.model.UserWorker;
import school.sptech.server.repository.CategoryRepository;
import school.sptech.server.service.ExportTxt;
import school.sptech.server.service.FilaObj;
import school.sptech.server.service.UserService;

import static org.springframework.http.ResponseEntity.status;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService dbUserService;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @PostMapping("/login")
    public ResponseEntity<Object> getLoginUser(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String userLogin = user.getEmail();
        String userPassword = user.getPassword();
        user = dbUserService.loginUser(userLogin,userPassword);
        if(user == null){
            return status(404).build();
        }

        return status(200).body(user);
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
    public ResponseEntity getReport() throws IOException {

        ExportTxt ex = new ExportTxt();

        List<User> users = dbUserService.getAll();
        List<Category> categories = categoryRepository.findAll();

        return status(200)
                .header("content-type", "text/txt")
                // .header("content-length", "9999999999")
                .header("content-disposition", "filename=\"report.txt\"")
                .body(ex.gravaArquivoTxt(users,categories,"report.txt"));
    }

    @GetMapping(value = "/worker/categories/{id}")
    public ResponseEntity<List<Category>> getWorkerCategories(@PathVariable Integer id) {
        List<Category> categories = dbUserService.getWorkerCategories(id);

        return categories.isEmpty() ? status(204).build() : status(200).body(categories);
    }

    @PatchMapping(value = "/report", consumes = "text/txt; charset: utf-8")
    public ResponseEntity importUsers(@RequestBody byte[] report) throws IOException {
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
        while (!fila.isEmpty()){
            if(fila.peek().getClass().toString().equals("class school.sptech.server.model.User")){
                dbUserService.saveUser((User) fila.poll());
            }
            if(fila.peek().getClass().toString().equals("class school.sptech.server.model.Category")){
                categoryRepository.save((Category) fila.poll());
            }

        }

        return ResponseEntity.status(200).body(document);
    }



}
