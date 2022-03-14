package school.sptech.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Staff> getStaff() {
        return staff;
    }

    @PostMapping()
    public String cadastrarStaff(@RequestBody Staff novoStaff) {

        try {

            bancoStaff.save((Staff) novoStaff);
            staff.add(novoStaff);
            return "Usuario cadastrado com sucesso";
        } catch (NullPointerException npe) {
            return "erro 405";
        }
    }

    @GetMapping("/login/{userStaffLogin}/{userPassword}")
    public String getLoginUser(@PathVariable String userLogin, @PathVariable String userPassword) {
        for (Staff userStaff : staff) {
            return userStaff.login(userLogin, userPassword);
        }
        return "Staff não encontrado";
    }


}
