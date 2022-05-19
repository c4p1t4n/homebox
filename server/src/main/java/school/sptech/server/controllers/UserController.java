package school.sptech.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.server.model.User;
import school.sptech.server.model.UserCustomer;
import school.sptech.server.model.UserWorker;
import school.sptech.server.repository.UserRepository;
import school.sptech.server.response.GeoApifyResponse;
import school.sptech.server.response.ViaCepResponse;
import school.sptech.server.rest.ClienteViaCep;
import school.sptech.server.service.UserService;

import java.text.Normalizer;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService dbUserService;

    @Autowired
    private ClienteViaCep clienteViaCep;


    @PostMapping("/customer")
    public ResponseEntity registerUserCustomer(@RequestBody UserCustomer newUser) {

        if (newUser.getType().equals("customer")) {
            newUser.setAuthenticated('n');
            dbUserService.saveUser(newUser);
        } else {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.status(201).build();

    }

    @GetMapping("/customer")
    public ResponseEntity<List<User>> getUserCustomer() {
        return !dbUserService.getAllCustomer().isEmpty() ? ResponseEntity.status(200).body(dbUserService.getAllCustomer()) : ResponseEntity.status(204).build();
    }

    @PostMapping("/worker")
    public ResponseEntity registerUserWorker(@RequestBody UserWorker newUser) {
        try {

            if (newUser.getType().equals("worker")) {
                newUser.setAuthenticated('n');
                dbUserService.saveUser(newUser);
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

        return !dbUserService.getAllWorkers().isEmpty() ? ResponseEntity.status(200).body(dbUserService.getAllWorkers()): ResponseEntity.status(204).build();
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
          return !dbUserService.getAll().isEmpty() ? ResponseEntity.status(200).body(dbUserService.getAll()) : ResponseEntity.status(204).build();
    }

    @GetMapping("/login/{userLogin}/{userPassword}")
    public ResponseEntity getLoginUser(@PathVariable String userLogin, @PathVariable String userPassword) {
        List<User> users = dbUserService.getAll();

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

        List<User> users = dbUserService.getAll();

        for (User user : users) {
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


    @GetMapping("worker/report")
    public ResponseEntity getReport() {
        String report = "";

        List<User> list = dbUserService.getAllWorkers();
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

    @PatchMapping("/lonLat/{cep}")
    public ResponseEntity testCep(@PathVariable String cep){
        ViaCepResponse viaCeprResponse = clienteViaCep.getCep(cep);
        GeoApifyResponse geoApifyResponse = new GeoApifyResponse();

        geoApifyResponse.getLonLat(viaCeprResponse.getLogradouro(),
                                    cep,
                                    viaCeprResponse.getLocalidade(),
                                    viaCeprResponse.getUf());

        return ResponseEntity.status(200).body(geoApifyResponse);
    }

    @GetMapping("calcdist/{longitude1}/{latitude1}/{longitude2}/{latitude2}")
    public ResponseEntity hversine(@PathVariable Double longitude1,
                                   @PathVariable Double latitude1,
                                   @PathVariable Double longitude2,
                                   @PathVariable Double latitude2){

        Double long1 = toRad(longitude1);
        Double lati1 = toRad(latitude1);
        Double long2 = toRad(longitude2);
        Double lati2 = toRad(latitude2);

        Double dlon = long2 - long1;
        Double dlat = lati2 - lati1;

        Double sinDlatSquare = Math.pow(Math.sin(dlat/2),2);
        Double sinDlonSquare = Math.pow(Math.sin(dlon/2),2);

        Double a = sinDlatSquare + Math.cos(lati1) * Math.cos(lati2) * sinDlonSquare;

        Double b = 2*(Math.asin(Math.sqrt(a)));

        return ResponseEntity.status(200).body(6371 * b);
    }

    private static Double toRad(Double value) {
        return (value * Math.PI) / 180;
    }
}
