package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.Staff;
import school.sptech.server.repository.StaffRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffRepository bancoStaff;
    List<Staff> staff = new ArrayList<>();

    @GetMapping()
    public ResponseEntity<List<Staff>> getStaff() {
        List<Staff> users = bancoStaff.findAll();
        return !users.isEmpty() ? ResponseEntity.status(200).body(users) : ResponseEntity.status(204).build();
    }

    @PostMapping()
    public ResponseEntity cadastrarStaff(@RequestBody Staff novoStaff) {

        try {
            bancoStaff.save((Staff) novoStaff);
            staff.add(novoStaff);
            return ResponseEntity.status(200).build();
        } catch (NullPointerException npe) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/login/{userStaffLogin}/{userPassword}")
    public ResponseEntity getLoginUser(@PathVariable String userLogin, @PathVariable String userPassword) {
        for (Staff userStaff : staff) {
            if (userStaff.login(userLogin, userPassword)) {
                return ResponseEntity.status(200).build();
            } else {
                return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.status(401).build();
    }

}
