package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.Staff;
import school.sptech.server.model.User;
import school.sptech.server.repository.StaffRepository;
import school.sptech.server.request.LoginRequest;
import school.sptech.server.service.UserService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffRepository dbRepositoryStaff;
    @Autowired
    private UserService dbServiceUser;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<Staff>> getStaff() {
        List<Staff> users = dbRepositoryStaff.findAll();
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(204).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping()
    public ResponseEntity<Object> cadastrarStaff(@RequestBody Staff newStaff) {

        try {
            dbRepositoryStaff.save(newStaff);

            return ResponseEntity.status(200).build();
        } catch (NullPointerException npe) {
            return ResponseEntity.status(400).build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<Staff> login(@RequestBody LoginRequest loginCredentials)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Staff staff = dbServiceUser.staffLogin(loginCredentials.getEmail(), loginCredentials.getPassword());

        if (staff != null)
            return ResponseEntity.status(200).body(staff);

        return ResponseEntity.status(404).build();
    }

}
